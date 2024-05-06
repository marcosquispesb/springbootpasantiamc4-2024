package com.example.springbootdemo.services.impl;

import com.example.springbootdemo.model.AuthUser;
import com.example.springbootdemo.model.Holiday;
import com.example.springbootdemo.model.License;
import com.example.springbootdemo.repository.AuthUserRepository;
import com.example.springbootdemo.repository.HolidayRepository;
import com.example.springbootdemo.repository.LicenceRepository;
import com.example.springbootdemo.repository.RegisterDayRepository;
import com.example.springbootdemo.rest.dto.AtrasoPorUsuario;
import com.example.springbootdemo.rest.exceptions.DataNotFoundException;
import com.example.springbootdemo.services.MailService;
import com.example.springbootdemo.services.ProcessService;
import com.example.springbootdemo.utils.DateRange;
import com.example.springbootdemo.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AuthRoleServiceImpl
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Slf4j
@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private AuthUserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private RegisterDayRepository registerDayRepository;

    @Autowired
    private LicenceRepository licenceRepository;

    @Override
    public void processAtrasos(List<String> jisUsernames) {
        String transactionId = "" + System.currentTimeMillis();
        //AuthUser userJis = userRepository.findByUsername("bruce");

        AuthUser userJis = userRepository.findByUsername(jisUsernames.get(0));
        if (userJis == null) {
            log.error("No se encontro el usuario");
            //continue; para continuar con el sig item del for jisIds
        }

        List<AuthUser> users = userRepository.findByUserJis(userJis);
        List<Long> userIds = users.stream().map(AuthUser::getId).collect(Collectors.toList());

        Date dateStart = DateUtil.formatToFirstDayMonth(new Date());
        Date dateEnd = DateUtil.formatToEnd(new Date());

        List<Date> holidays = holidayRepository.findAllByDates(dateStart, dateEnd).stream()
                .map(Holiday::getDate).collect(Collectors.toList());

        List<License> licencesAll = licenceRepository.findAllByDates(userIds, dateStart, dateEnd);

        List<Date> datesValid = DateUtil.getDatesValidsLuVi(dateStart, dateEnd, holidays);

        String htmlMain = readFromResourcesThree("html\\mail.template.atrasos.content.html");
        String htmlRowBase = readFromResourcesThree("html\\mail.template.atrasos.tablerow.html");
        List<String> htmlRows = new ArrayList<>();
        int totalUsuariosAtrasados = 0;
        int totalUsuariosCumplidos = 0;
        for (AuthUser user : users) {
            log.info(user.getName() + " " + user.getLastname());
            List<DateRange> registerDays = registerDayRepository.findAllByUser_IdOrderByIdAsc(user.getId()).stream()
                    .map(x -> new DateRange(x.getId(), x.getDateStart(), x.getDateEnd()))
                    .collect(Collectors.toList());
            List<DateRange> licenses = licencesAll.stream()
                    .filter(x -> x.getUser().getId().equals(user.getId()))
                    .map(x -> new DateRange(x.getId(), x.getDateStart(), x.getDateEnd()))
                    .collect(Collectors.toList());

            List<AtrasoPorUsuario> atrasoPorUsuarioList = getAtrasosYFaltas(registerDays, licenses, datesValid);
            if (!atrasoPorUsuarioList.isEmpty()) {
                Long totalMinutosAtrasados = atrasoPorUsuarioList.stream().map(AtrasoPorUsuario::getMinutosAtraso).reduce(0L, Long::sum);
                List<String> atrasos = atrasoPorUsuarioList.stream().
                        map(x -> DateUtil.toString(x.getDate(), DateUtil.FORMAT_DATE_G) + ": " + x.getMinutosAtraso())
                        .collect(Collectors.toList());
                String totalAtrasosStr = String.join(",", atrasos);
                String htmlRow = htmlRowBase
                        .replace("{user}", user.getName() + " " + user.getLastname())
                        .replace("{faltas_hrs}", totalAtrasosStr)
                        .replace("{minutos_atrasados}", ""+totalMinutosAtrasados);
                htmlRows.add(htmlRow);
            }
        }

        // HTML MAIN
        String htmlResult = htmlMain
                .replace("{mes}", "" + DateUtil.toString(new Date(), DateUtil.FORMAT_MM))
                .replace("{rows}", "" + String.join(",", htmlRows))
                .replace("{total_atrasados}", "" + totalUsuariosAtrasados)
                .replace("{total_cumplidos}", "" + totalUsuariosCumplidos);

        // TODO enviar correo de forma asincrona
        mailService.sendMail(transactionId, htmlResult, true);

        // TODO adaptar la solucion para una lista de jisUsernames y enviar correos correspondientes.
        // TODO si para el mes actual ya se envio correo a un jis, siel proceso se vuelve a ejecutar,
        //      ya no se debe volver a enviar correo
    }

    public List<AtrasoPorUsuario> getAtrasosYFaltas(List<DateRange> registerDays, List<DateRange> licenses, List<Date> datesValid) {
        List<AtrasoPorUsuario> result = new ArrayList<>();
        for (Date dateValid : datesValid) {
            List<DateRange> registerDaysOneDay = registerDays.stream().filter(x -> DateUtil.equals(x.getDateStart(), dateValid, DateUtil.FORMAT_DATE)
                    || DateUtil.equals(x.getDateEnd(), dateValid, DateUtil.FORMAT_DATE)).collect(Collectors.toList());
            List<DateRange> licensesOneDay = licenses.stream().filter(x -> DateUtil.dateIsBetween(dateValid, x.getDateStart(), x.getDateEnd())).collect(Collectors.toList());

            AtrasoPorUsuario atrasoPorUsuario = getAtrasoPorUsuario(dateValid, registerDaysOneDay, licensesOneDay);
            if (atrasoPorUsuario != null && (atrasoPorUsuario.getHrsFalta() > 0 || atrasoPorUsuario.getMinutosAtraso() > 0)) {
                result.add(atrasoPorUsuario);
            }
        }

        return result;
    }

    private AtrasoPorUsuario getAtrasoPorUsuario(Date date, List<DateRange> registerDaysOneDay, List<DateRange> licensesOneDay) {
        String dateStr = DateUtil.toString(date, DateUtil.FORMAT_DATE_G);

        if (registerDaysOneDay.isEmpty() && licensesOneDay.isEmpty()) //no hay registros ni licencias
            AtrasoPorUsuario.builder().hrsFalta(8).build();

        long hrsTrabajoEsperado = 8;
        if (licensesOneDay.isEmpty()) { // TODO SIN Licencia
            hrsTrabajoEsperado = 8;
        } else if (licensesOneDay.size() == 1) { // TODO Con Licencia
            if (DateUtil.equalsDate(licensesOneDay.get(0).getDateStart(), licensesOneDay.get(0).getDateEnd())) { // mismo dia
                long diffHrs = DateUtil.diferenciaHoras(licensesOneDay.get(0).getDateStart(), licensesOneDay.get(0).getDateEnd());
                if (8 - diffHrs <= 0) { // Licencia dia completo
                    hrsTrabajoEsperado = 0;
                } else { // licencia parcial
                    hrsTrabajoEsperado = 8 - diffHrs;
                }
            } else { // licencia con rango de dias
                log.warn("considerar licencia por rango de dias: " + dateStr);
            }
        } else {
            log.error("usuario se falto y hay mas de una licencia para el dia: " + dateStr);
        }

        long hrsTrabajadas = 0;
        long minutosAtrasados = 0;
        for (DateRange registerDay : registerDaysOneDay) {
            if (registerDay.getDateStart() != null && registerDay.getDateEnd() != null) {
                hrsTrabajadas += DateUtil.diferenciaHoras(registerDay.getDateStart(), registerDay.getDateEnd());

                int hrEntrada = DateUtil.getHour(date) > 12 ? 14 : 8;
                Date dateEnter = DateUtil.formatToTime(registerDay.getDateStart(), hrEntrada, 0, 0, 0);
                long diffMinutes = DateUtil.diferenciaMinutos(dateEnter, registerDay.getDateStart());
                if (diffMinutes > 0)
                    minutosAtrasados += diffMinutes;
            } else {
                String dateStartStr = DateUtil.toString(registerDay.getDateStart(), DateUtil.FORMAT_DATE_G);
                String dateEndStr = DateUtil.toString(registerDay.getDateEnd(), DateUtil.FORMAT_DATE_G);
                log.error("hay registro incompleto. id: {}, fechaInicio: {}, fechaFin: {}", registerDay.getId(), dateStartStr, dateEndStr);
            }
        }

        int hrsFalta = 0;
        if (hrsTrabajadas < hrsTrabajoEsperado)
            hrsFalta = (int)(hrsTrabajoEsperado - hrsTrabajadas);

        return AtrasoPorUsuario.builder()
                .date(date)
                .hrsFalta(hrsFalta)
                .minutosAtraso(minutosAtrasados)
                .build();
    }

    public static String readFromResourcesThree(String fileName) {
        String data = "No se logro leer el archivo " + fileName + " de resources.";
        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource(fileName).getInputStream();
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            data = new String(bdata, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    log.error("Error al guardar al cerrar el inputstream. Causa: " + e.getMessage());
                }
            }
        }
        return data;
    }
}

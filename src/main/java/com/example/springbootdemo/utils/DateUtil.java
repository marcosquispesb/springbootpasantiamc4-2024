package com.example.springbootdemo.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * DateUtil
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Slf4j
public class DateUtil {
    public static final String FORMAT_FECHA_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    //public static final String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss"; // 2024-04-01 08:00:00
    public static final String FORMAT_DATE = "yyyyMMdd";
    public static final String FORMAT_DATE_D = "yyyy/MM/dd";
    public static final String FORMAT_DATE_G = "yyyy-MM-dd";
    public static final String FORMAT_MM = "MM";

    public static Date toDate(String valor, String format) {
        if (valor == null || valor.trim().length() == 0)
            return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(valor);
        } catch (Exception ex) {
            log.info("Error inesperado al parsear string a date", ex);
            return null;
        }
    }

    public static String toString(Date valor, String format) {
        if (valor == null)
            return null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(valor);
        } catch (Exception ex) {
            log.error("Error al convertir date a string", ex);
            return null;
        }
    }

    public static Date formatToTime(Date date, int hour, int minute, int second, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

    public static Date formatToStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date formatToEnd(Date date)  {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date formatToFirstDayMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date formatToLastDayMonth(Date date)  {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date addDays(Date date, int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        return calendar.getTime();
    }

    public static boolean equals(Date date1, Date date2, String format){
        if (date1 == null && date2 == null){
            return true;
        } else if (date1 != null && date2 != null) {
            String date1Str = toString(date1, format);
            String date2Str = toString(date2, format);
            return date1Str.equals(date2Str);
        }
        return false;
    }

    public static boolean equalsDate(Date date1, Date date2){
        if (date1 == null && date2 == null){
            return true;
        } else if (date1 != null && date2 != null) {
            String date1Str = toString(date1, FORMAT_DATE);
            String date2Str = toString(date2, FORMAT_DATE);
            return date1Str.equals(date2Str);
        }
        return false;
    }

    public static boolean dateIsBetween(Date fecha, Date fechaInicio, Date fechaFin) {
        if (fecha == null || fechaInicio == null || fechaFin == null)
            return false;

        fecha = formatToStart(fecha);
        fechaInicio = formatToStart(fechaInicio);
        fechaFin = formatToStart(fechaFin);

        if (fechaInicio.compareTo(fechaFin) > 0)
            return false;

        if (fecha.compareTo(fechaInicio) < 0 || fecha.compareTo(fechaFin) > 0)
            return false;

        return true;
    }

    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static List<Date> getDatesValidsLuVi(Date dateStart, Date dateEnd, List<Date> datesToExclude) {
        for (int i = 0; i < datesToExclude.size(); i++) {
            datesToExclude.set(i, formatToStart(datesToExclude.get(i)));
        }

        List<Date> result = new ArrayList<>();
        dateEnd = formatToEnd(dateEnd);
        for (Date date = dateStart; !date.after(dateEnd); date = addDays(date, 1)) {
            int dayOfWeek = getDayOfWeek(date);
            if (dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY) {
                boolean exclude = false;
                for (Date dateExclude : datesToExclude) {
                    if (equals(date, dateExclude, FORMAT_DATE)) {
                        exclude = true;
                        break;
                    }
                }
                if (!exclude)
                    result.add(date);
            }
        }
        return result;
    }

    public static long diferenciaHoras(Date fechaInicial, Date fechaFin) {
        long diffInMillies = Math.abs(fechaFin.getTime() - fechaInicial.getTime());
        return TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static long diferenciaMinutos(Date fechaInicial, Date fechaFin) {
        long diffInMillies = Math.abs(fechaFin.getTime() - fechaInicial.getTime());
        return TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static long getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR);
    }
}

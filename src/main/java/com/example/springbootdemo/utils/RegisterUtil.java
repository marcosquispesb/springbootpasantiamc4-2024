package com.example.springbootdemo.utils;

import com.example.springbootdemo.rest.dto.RegisterDayDto;

import java.util.*;
import java.util.regex.Pattern;

/**
 * RegisterUtil
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class RegisterUtil {
    private static int POS_USERNAME = 0;
    private static int POS_DATE = 1;
    private static int POS_REGISTERTYPE = 2;

    public static List<RegisterDayDto> convertToDtos(Scanner scanner) {
        List<RegisterDayDto> dtos = new ArrayList<>();
        Map<String, List<String[]>> registersMap = new HashMap<>();
        String line;
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine().trim();
            String[] dataArray = line.split(Pattern.quote(","));
            if (line.isEmpty())
                continue;

            if (!registersMap.containsKey(dataArray[POS_USERNAME]))
                registersMap.put(dataArray[POS_USERNAME], new ArrayList<>());

            registersMap.get(dataArray[POS_USERNAME]).add(dataArray);
            System.out.println(Arrays.toString(dataArray));
        }

        for (Map.Entry<String, List<String[]>> entry : registersMap.entrySet()) {
            String userName = entry.getKey();
            int i = 0;
            while(i < entry.getValue().size()) {
                String[] dataArray = entry.getValue().get(i);

                RegisterDayDto dto = new RegisterDayDto();
                dto.setUserName(userName);
                if (dataArray[POS_REGISTERTYPE].equalsIgnoreCase("INGRESO")) {
                    dto.setDateStart(DateUtil.toDate(dataArray[POS_DATE], DateUtil.FORMAT_DATE_TIME));
                    if (i + 1 < entry.getValue().size()) {
                        String[] nextArray = entry.getValue().get(i + 1);
                        if (nextArray[POS_REGISTERTYPE].equalsIgnoreCase("SALIDA")) {
                            dto.setDateEnd(DateUtil.toDate(nextArray[POS_DATE], DateUtil.FORMAT_DATE_TIME));
                            i += 1;
                        }
                    }
                } else { // SALIDA
                    dto.setDateEnd(DateUtil.toDate(dataArray[POS_DATE], DateUtil.FORMAT_DATE_TIME));
                }
                i++;
                dtos.add(dto);
            }
        }

        System.out.println(registersMap.size());
        System.out.println(dtos);
        return dtos;
    }
}

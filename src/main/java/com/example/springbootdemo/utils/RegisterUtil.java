package com.example.springbootdemo.utils;

import com.example.springbootdemo.rest.dto.RegisterDayDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * RegisterUtil
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class RegisterUtil {

    public static List<RegisterDayDto> convertToDtos(Scanner scanner) {
        List<RegisterDayDto> dtos = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] lines = scanner.nextLine().trim().split(Pattern.quote(","));
            System.out.println(Arrays.toString(lines));

            // implementar logica principal
            // buscar ingreso y salida de usuario

            RegisterDayDto dto = new RegisterDayDto();
            dto.setDateStart(null);
            dto.setDateEnd(null);
            dto.setUserName("");

            dtos.add(dto);
        }
        return dtos;
    }
}

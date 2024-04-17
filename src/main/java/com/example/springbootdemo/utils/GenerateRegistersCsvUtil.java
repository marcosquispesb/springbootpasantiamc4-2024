package com.example.springbootdemo.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GenerateRegistersCsvUtil
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class GenerateRegistersCsvUtil {

    public static List<String> generateRegistersByDayAllUsers(List<String> dates) {
        List<String> userNameList = Arrays.asList("Oliver", "Barry", "Bruce");
        //bruce,2024-04-01 08:00:00,INGRESO
        List<String> result = new ArrayList<>();
        for (String date : dates) {
            for (String userName : userNameList) {
                String[] dataArray = new String[] {userName,  date+ " 08:00:00", "INGRESO"};
                result.add(String.join(",", dataArray));

                dataArray = new String[] {userName,  date+ " 12:00:00", "SALIDA"};
                result.add(String.join(",", dataArray));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> contentList = generateRegistersByDayAllUsers(Arrays.asList("2024-04-02", "2024-04-17"));
        contentList.stream().forEach(System.out::println);
        // System.out.println(contentList);

        // servicios para licences  (por usuario)
        // servicios para holidays  (para todos los usuarios)
        // servicio para modificar los jis por usuario

        // 1. generar las fechas en formato "yyyy-MM-dd" dado un rango de fechas
        // 2. generar aleatoriedad en los minutos de entradas o salidas, para que haya minutos de atraso y otros
        // 3. exportar resultado en un archivo csv con el formato de los rangos de fecha
        //      ej: registros-masivo-20240402-20240417.csv

        // probar envio de correo con springboot
    }
}

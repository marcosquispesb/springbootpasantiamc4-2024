package com.example.springbootdemo.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

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
}

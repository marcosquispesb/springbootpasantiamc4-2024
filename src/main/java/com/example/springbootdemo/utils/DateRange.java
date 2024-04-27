package com.example.springbootdemo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * DateRange
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class DateRange {
    private Object id;
    private Date dateStart;
    private Date dateEnd;
}

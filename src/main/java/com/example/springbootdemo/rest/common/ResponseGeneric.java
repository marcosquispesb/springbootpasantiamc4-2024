package com.example.springbootdemo.rest.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * ResponseBody
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
public class ResponseGeneric<T> implements Serializable {

    private String code;

    private String message;

    private T data;
}

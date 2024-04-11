package com.example.springbootdemo.rest.common;

/**
 * ApiUtil
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class ApiUtil {

    public static final String OK_CODE = "OK";
    public static final String ERROR_CODE = "NOK";
    public static final String OK_MESSAGE = "Solicitud finalizada con éxito.";
    public static final String INTERNAL_SERVER_ERROR = "Ocurrió un error inesperado, contactese con el administrador.";

    public static <T> ResponseGeneric<T> responseOk() {
        return ResponseGeneric.<T>builder()
                .code(OK_CODE)
                .message(OK_MESSAGE)
                .build();
    }

    public static <T> ResponseGeneric<T> responseOk(T data) {
        return ResponseGeneric.<T>builder()
                .code(OK_CODE)
                .message(OK_MESSAGE)
                .data(data)
                .build();
    }

    public static <T> ResponseGeneric<T> responseError500() {
        return ResponseGeneric.<T>builder()
                .code(ERROR_CODE)
                .message(INTERNAL_SERVER_ERROR)
                .build();
    }

    public static <T> ResponseGeneric<T> responseError(String message) {
        return ResponseGeneric.<T>builder()
                .code(ERROR_CODE)
                .message(message)
                .build();
    }
}

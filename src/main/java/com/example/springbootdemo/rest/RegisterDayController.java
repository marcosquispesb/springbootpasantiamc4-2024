package com.example.springbootdemo.rest;

import com.example.springbootdemo.model.AuthRole;
import com.example.springbootdemo.model.RegisterDay;
import com.example.springbootdemo.rest.common.ApiUtil;
import com.example.springbootdemo.rest.common.ResponseGeneric;
import com.example.springbootdemo.rest.dto.RegisterDayDto;
import com.example.springbootdemo.rest.exceptions.DataNotFoundException;
import com.example.springbootdemo.rest.exceptions.OperationException;
import com.example.springbootdemo.services.AuthRoleService;
import com.example.springbootdemo.services.RegisterDayService;
import com.example.springbootdemo.utils.RegisterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.springframework.http.ResponseEntity.ok;

/**
 * RegisterDayController
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/register-days")
public class RegisterDayController {

    @Autowired
    private RegisterDayService registerDayService;

    @GetMapping("")
    public ResponseEntity<ResponseGeneric<List<RegisterDay>>> listAll() {
        log.info("register day All");
        try {
            return ok(ApiUtil.responseOk(registerDayService.findAll()));
        } catch (OperationException | DataNotFoundException e) {
            log.error("Error controlado al listar causa: {}", e.getMessage());
            return ok(ApiUtil.responseError(e.getMessage()));
        } catch (Exception e) {
            log.error("Error inesperado al listar", e);
            return ok(ApiUtil.responseError500());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseGeneric<List<RegisterDay>>> listAllByUser(@PathVariable(value = "userId") Long userId) {
        log.info("register day All by userId");
        try {
            return ok(ApiUtil.responseOk(registerDayService.findAllByUserId(userId)));
        } catch (OperationException | DataNotFoundException e) {
            log.error("Error controlado al listar by user causa: {}", e.getMessage());
            return ok(ApiUtil.responseError(e.getMessage()));
        } catch (Exception e) {
            log.error("Error inesperado al listar by user", e);
            return ok(ApiUtil.responseError500());
        }
    }

    @PostMapping("")
    public ResponseEntity<ResponseGeneric<Long>> save(@RequestBody RegisterDay dto) {
        log.info("register day save");
        try {
            return ok(ApiUtil.responseOk(registerDayService.save(dto)));
        } catch (OperationException | DataNotFoundException e) {
            log.error("Error controlado al guardar causa: {}", e.getMessage());
            return ok(ApiUtil.responseError(e.getMessage()));
        } catch (Exception e) {
            log.error("Error inesperado al guardar", e);
            return ok(ApiUtil.responseError500());
        }
    }

    @RequestMapping(value = "/carga-masiva", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity cargaMasivo(@RequestPart("fileRegisters") @Nullable MultipartFile request) {
        log.info("Iniciando carga registros masiva");
        try {
            Scanner scRegisters;
            try {
                scRegisters = new Scanner(request.getInputStream(), StandardCharsets.UTF_8.name());
            } catch (IOException e) {
                log.error("Error al leer obtener el archivo {}", request.getOriginalFilename(), e);
                throw new OperationException("No se logro leer el archivo");
            }

            List<RegisterDayDto> dtos = RegisterUtil.convertToDtos(scRegisters);
            registerDayService.save(dtos);

            return ResponseEntity.ok().build();
        } catch (OperationException  e) {
            log.error("Error al procesar carga masiva {}", e.getMessage());
            return ResponseEntity.badRequest().body(ApiUtil.responseError(e.getMessage()));
        } catch (Exception e) {
            log.error("Error inesperado al procesar carga masiva", e);
            return ResponseEntity.badRequest().body(ApiUtil.responseError500());
        }
    }
}

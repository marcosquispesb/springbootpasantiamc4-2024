package com.example.springbootdemo.rest;

import com.example.springbootdemo.model.AuthResource;
import com.example.springbootdemo.rest.common.ApiUtil;
import com.example.springbootdemo.rest.common.ResponseGeneric;
import com.example.springbootdemo.rest.dto.AuthResourceDto;
import com.example.springbootdemo.services.AuthResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * AuthResourceController
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/resources")
public class AuthResourceController {

    @Autowired
    @Qualifier("AuthResourceServiceImpl")
    private AuthResourceService authResourceService;


    @GetMapping("")
    public ResponseEntity<ResponseGeneric<List<AuthResource>>> list() {
        log.info("list");
        try {
            return ok(ApiUtil.responseOk(authResourceService.findAll()));
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().body(ApiUtil.responseError500());
        }
    }

    @GetMapping("/menu")
    public ResponseEntity<ResponseGeneric<List<AuthResourceDto>>> listMenu() {
        log.info("menu");
        try {
            // implementar solucion
            return ok(ApiUtil.responseOk(new ArrayList<>()));
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().body(ApiUtil.responseError500());
        }
    }

    @GetMapping("/menuByRole/{roleId}")
    public ResponseEntity<ResponseGeneric<List<AuthResourceDto>>> listMenuByRole(@PathVariable("roleId") Long roleId) {
        log.info("menuByRole");
        try {
            // implementar solucion
            return ok(ApiUtil.responseOk(new ArrayList<>()));
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().body(ApiUtil.responseError500());
        }
    }
}

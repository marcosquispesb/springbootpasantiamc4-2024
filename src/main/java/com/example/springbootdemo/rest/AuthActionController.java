package com.example.springbootdemo.rest;

import com.example.springbootdemo.model.AuthAction;
import com.example.springbootdemo.services.AuthActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * AuthActionController
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/actions")
public class AuthActionController {

    @Autowired
    private AuthActionService authActionService;

    @GetMapping("/test")
    public String test() {
        log.info("test!!");
        return "Hola mundo!!";
    }

    @GetMapping("")
    public ResponseEntity<List<AuthAction>> list() {
        //UserResponse2 response = new UserResponse2("nok", "", Collections.emptyList());
        log.info("user All");
        try {
            return ok(authActionService.findAll());
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthAction> getByCode(@PathVariable("id") Long id) {
        log.info("user get by code");
        try {
            AuthAction authAction = authActionService.getById(id);
            return ok(authAction);
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/getByCode")
    public ResponseEntity<AuthAction> getByCode(@RequestParam("code") String code) {
        log.info("user get by code");
        try {
            AuthAction authAction = authActionService.findByCode(code);
            return ok(authAction);
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

}

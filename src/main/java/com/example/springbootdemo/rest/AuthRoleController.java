package com.example.springbootdemo.rest;

import com.example.springbootdemo.model.AuthRole;
import com.example.springbootdemo.services.AuthRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * AuthRoleController
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/roles")
public class AuthRoleController {

    @Autowired
    private AuthRoleService authRoleService;

    @GetMapping("")
    public ResponseEntity<List<AuthRole>> list() {
        log.info("user All");
        try {
            return ok(authRoleService.findAll());
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

}

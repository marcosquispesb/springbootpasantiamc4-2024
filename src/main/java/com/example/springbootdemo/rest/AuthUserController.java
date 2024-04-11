package com.example.springbootdemo.rest;

import com.example.springbootdemo.model.AuthUser;
import com.example.springbootdemo.rest.common.ApiUtil;
import com.example.springbootdemo.rest.common.ResponseGeneric;
import com.example.springbootdemo.rest.dto.AuthUserDto;
import com.example.springbootdemo.rest.dto.UserDto;
import com.example.springbootdemo.rest.exceptions.DataNotFoundException;
import com.example.springbootdemo.rest.exceptions.OperationException;
import com.example.springbootdemo.services.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * AuthUserController
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    @GetMapping("/test")
    public String test() {
        log.info("test!!");
        return "Hola mundo!!";
    }

    @GetMapping("")
    public ResponseEntity<ResponseGeneric<List<AuthUser>>> list() {
        log.info("user All");
        try {
            return ok(ApiUtil.responseOk(authUserService.findAll()));
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.internalServerError().body(ApiUtil.responseError500());
        }
    }

    @GetMapping("/listFull")
    public ResponseEntity<List<AuthUserDto>> listFull() {
        //UserResponse2 response = new UserResponse2("nok", "", Collections.emptyList());
        log.info("user All");
        try {
            //response = new UserResponse2("ok", "", authActionService.findAll());
            return ok(authUserService.findAllDto());
        } catch (Exception e) {
            log.error("Error inesperado", e);
            //response.setMessage("Error inesperado al listar");
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<ResponseGeneric<Long>> create(@RequestBody AuthUserDto dto) {
        log.info("create");
        try {
            Long id = authUserService.save(dto);
            return ok(ApiUtil.responseOk(id));
        } catch (OperationException | DataNotFoundException e) {
            log.error("Error message: {}", e.getMessage());
            return ResponseEntity.badRequest().body(ApiUtil.responseError(e.getMessage()));
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().body(ApiUtil.responseError500());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGeneric> update(@PathVariable("id") Long id, @RequestBody AuthUserDto dto) {
        log.info("update");
        try {
            dto.setId(id);
            authUserService.save(dto);
            return ok(ApiUtil.responseOk());
        } catch (DataNotFoundException | OperationException e) {
            log.error("{} message: {}", e.getClass().getSimpleName(), e.getMessage());
            return ResponseEntity.badRequest().body(ApiUtil.responseError(e.getMessage()));
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().body(ApiUtil.responseError500());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        log.info("delete");
        try {
            authUserService.delete(id);
            return ok().build();
        } catch (DataNotFoundException e) {
            log.error("DataNotFoundException message: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().build();
        }
    }
}

package com.example.springbootdemo.rest;

import com.example.springbootdemo.rest.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

/**
 * DemoController
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @GetMapping
    public String test() {
        log.info("test!!");
        return "Hola mundo!!";
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<String> user(@PathVariable(value = "name") String name, @RequestParam(value = "age") Integer age) {
        return ok().body(String.format("Hola %s %d!", name, age));
    }

    @GetMapping("/user")
    public ResponseEntity<UserDto> user(@RequestParam(value = "codigo") Integer codigo, @RequestBody UserDto userDto) {
        if (userDto == null || userDto.getAddress() == null)
            return ResponseEntity.badRequest().build();
        //return "user Error: no envio el nombreValor en el body";
        log.info("user [{}] [{}]", userDto.getName(), codigo);
        return ok(userDto);
    }

}

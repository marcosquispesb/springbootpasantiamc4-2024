package com.example.springbootdemo.rest;

import com.example.springbootdemo.rest.common.ResponseGeneric;
import com.example.springbootdemo.rest.dto.UserDto;
import com.example.springbootdemo.services.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.badRequest;
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

    @GetMapping("/user1/{name}")
    public ResponseEntity<String> user(@PathVariable(value = "name") String name, @RequestParam(value = "age") Integer age) {
        return ok().body(String.format("Hola %s %d!", name, age));
    }

    @GetMapping("/user2")
    public ResponseEntity<UserDto> user(@RequestParam(value = "codigo") Integer codigo, @RequestBody UserDto userDto) {
        if (userDto == null || userDto.getAddress() == null)
            return ResponseEntity.badRequest().build();
        //return "user Error: no envio el nombreValor en el body";
        log.info("user [{}] [{}]", userDto.getName(), codigo);
        return ok(userDto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseGeneric<UserDto>> user(@PathVariable(value = "id") Long id) {
        if (id <= 0) { // id invalido
            //return badRequest().body(new UserResponse(false, "Error. id invalido", null, null));
            return badRequest().body(new ResponseGeneric<>("OK", "Error. id invalido", null));
        }

        ResponseGeneric<UserDto> response = new ResponseGeneric<UserDto>("OK", "operacion exitosa"
                , new UserDto("Oliver", "Atom", null, 28, null));
        return ok(response);
    }

    @Autowired
    private MailService mailService;

    @PostMapping(value = "/sendMail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendMail() {
        log.info("sendMail init!!");
        try {
            String transactionId = ""+System.currentTimeMillis();
            Boolean sended = mailService.sendMail(transactionId, "Contenido de prueba", false);
            log.info("sendMail success!!");
            return ok(sended);

        } catch (Exception e) {
            log.error("Error inesperado", e);
            return ResponseEntity.badRequest().build();
        }
    }
}

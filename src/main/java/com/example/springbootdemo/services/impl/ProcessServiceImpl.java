package com.example.springbootdemo.services.impl;

import com.example.springbootdemo.model.AuthRole;
import com.example.springbootdemo.model.AuthUser;
import com.example.springbootdemo.repository.AuthRoleRepository;
import com.example.springbootdemo.repository.AuthUserRepository;
import com.example.springbootdemo.services.AuthRoleService;
import com.example.springbootdemo.services.MailService;
import com.example.springbootdemo.services.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * AuthRoleServiceImpl
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Slf4j
@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private AuthUserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Override
    public void processAtrasos() {
        String transactionId = "" + System.currentTimeMillis();
        AuthUser userJis = userRepository.findByUsername("bruce");
        List<AuthUser> users = userRepository.findByUserJis(userJis);
        String html = readFromResourcesThree("html\\mail.template.atrasos.content.html");
        for (AuthUser user : users) {
            log.info(user.getName() + " " + user.getLastname());
        }
        mailService.sendMail(transactionId, html, true);
    }

    public static String readFromResourcesThree(String fileName) {
        String data = "No se logro leer el archivo " + fileName + " de resources.";
        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource(fileName).getInputStream();
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            data = new String(bdata, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    log.error("Error al guardar al cerrar el inputstream. Causa: " + e.getMessage());
                }
            }
        }
        return data;
    }
}
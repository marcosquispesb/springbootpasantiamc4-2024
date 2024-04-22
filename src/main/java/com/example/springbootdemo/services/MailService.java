package com.example.springbootdemo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.regex.Pattern;

/**
 * EmailService
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Slf4j
@Service
public class MailService {

    @Value("${spring.mail.username.from}")
    public String springMailUsernameFrom;

    @Value("${spring.mail.username.to}")
    public String springMailUsernameTo;

    @Value("${spring.mail.username.cc}")
    public String springMailUsernameCc;

    @Value("${spring.mail.subject}")
    public String springMailSubject;

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendMail(String transactionId, String content, boolean isContentHtml) {
        long timeMillis = System.currentTimeMillis();

        try {
            log.info(transactionId + " Se enviara mail");

            String[] arrayTo = convertToArray(springMailUsernameTo);
            if (arrayTo.length == 0) {
                log.error(transactionId + " Debe configurar al menos un destinatario.");
                return false;
            }

            String[] arrayCc = convertToArray(springMailUsernameCc);

            if (isContentHtml) {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setFrom(springMailUsernameFrom);
                helper.setTo(arrayTo);
                if (arrayCc.length > 0)
                    helper.setCc(arrayCc);

                helper.setSubject(springMailSubject);
                helper.setText(content, true);
                //helper.addAttachment("name_atachment", null);

                javaMailSender.send(mimeMessage);
            } else {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(springMailUsernameFrom);
                message.setTo(arrayTo);
                if (arrayCc.length > 0)
                    message.setCc(arrayCc);
                message.setSubject(springMailSubject);
                message.setText(content);

                javaMailSender.send(message);
            }
            timeMillis = System.currentTimeMillis() - timeMillis;
            log.info(transactionId + " El email se envio correctamente. time: " + timeMillis + " ms.");
            return true;

        } catch (Exception e) {
            timeMillis = System.currentTimeMillis() - timeMillis;
            log.error(transactionId + " El email no se pudo enviar. time: " + timeMillis + " ms.\n" + content, e);
            return false;
        }
    }

    private String[] convertToArray(String stringByCommas) {
        if (stringByCommas == null || stringByCommas.trim().length() == 0)
            return new String[0];
        String[] result = stringByCommas.trim().split(Pattern.quote(","));
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }
        return result;
    }

}

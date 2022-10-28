package com.example.school.service;

import com.example.school.email.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailSender {
private final JavaMailSender mailSender;
private final static Logger logger= LoggerFactory.getLogger(EmailService.class);

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    @Async
    public void sendEmail(String to, String email) {
     try {
         MimeMessage mimeMessage= mailSender.createMimeMessage();
         MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,"utf-8");
         helper.setText(email,true);
         helper.setFrom("yusuf@gmail.com");
         helper.setSubject("Confirm your email");
         mailSender.send(mimeMessage);

     } catch (MessagingException exception) {
         logger.error("Failed to send email for: " + email + "\n" + exception);
         throw new IllegalArgumentException("Failed to send email for: "+ email);
     }

    }

}

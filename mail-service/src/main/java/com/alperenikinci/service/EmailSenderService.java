package com.alperenikinci.service;

import com.alperenikinci.rabbitmq.model.EmailSenderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    public void sendMail(EmailSenderModel model){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("ikinci.alperen.06@gmail.com");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("Aktivasyon Kodunuz: ");
        mailMessage.setText(model.getActivationCode());
        javaMailSender.send(mailMessage);
    }
}

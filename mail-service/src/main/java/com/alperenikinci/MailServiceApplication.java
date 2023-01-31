package com.alperenikinci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class MailServiceApplication {

//    final JavaMailSender javaMailSender;

/*    public MailServiceApplication(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class,args);
    }

/*    @EventListener(ApplicationReadyEvent.class)
    public void sendMail(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("ikinci.alperen.06@gmail.com");
        mailMessage.setTo("ikinci.alperen.06@gmail.com");
        mailMessage.setSubject("Aktivasyon Kodunuz: ");
        mailMessage.setText("Af259");
        javaMailSender.send(mailMessage);
    }*/
}
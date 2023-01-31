package com.alperenikinci.rabbitmq.consumer;

import com.alperenikinci.rabbitmq.model.EmailSenderModel;
import com.alperenikinci.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailConsumer {

    private  final EmailSenderService emailSenderService;

    @RabbitListener(queues =("${rabbitmq.queueEmail}") )
    public void ActivationCode(EmailSenderModel model){
        log.info("Email:{}",model.toString());
        emailSenderService.sendMail(model);
    }
}

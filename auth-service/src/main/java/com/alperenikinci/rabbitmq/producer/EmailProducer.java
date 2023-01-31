package com.alperenikinci.rabbitmq.producer;


import com.alperenikinci.rabbitmq.model.EmailSenderModel;
import com.alperenikinci.rabbitmq.model.NewCreateUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailProducer {

    @Value("${rabbitmq.exchange-auth}")
    private String directExchange;
    @Value("${rabbitmq.bindingKeyEmail}")
    private String bindingKeyEmail;

    private  final RabbitTemplate rabbitTemplate;

    public void sendActivationCode(EmailSenderModel model){
        rabbitTemplate.convertAndSend(directExchange,bindingKeyEmail,model);
    }
}

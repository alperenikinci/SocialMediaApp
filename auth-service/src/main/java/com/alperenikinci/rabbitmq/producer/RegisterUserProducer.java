package com.alperenikinci.rabbitmq.producer;

import com.alperenikinci.rabbitmq.model.NewCreateUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserProducer {
    @Value("${rabbitmq.exchange-auth}")
    private String directExchange;
    @Value("${rabbitmq.bindingKeyRegister}")
    private String registerBindingKey;

    private  final RabbitTemplate rabbitTemplate;

    public void sendNewUser(NewCreateUserModel model){
        rabbitTemplate.convertAndSend(directExchange,registerBindingKey,model);
    }
}

package com.alperenikinci.rabbitmq.producer;

import com.alperenikinci.rabbitmq.model.UpdateUserProfileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserProducer {


    @Value("${rabbitmq.exchange-user}")
    private String directExchange;
    @Value("${rabbitmq.bindingKeyUpdate}")
    private String updateBindingKey;

    private  final RabbitTemplate rabbitTemplate;

    public void sendUpdateUser(UpdateUserProfileModel model){
        rabbitTemplate.convertAndSend(directExchange,updateBindingKey,model);
    }
}

package com.alperenikinci.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.exchange-user}")
    private String exchange;
    @Value("${rabbitmq.bindingKeyUpdate}")
    private String bindingKeyUpdate;
    @Value("${rabbitmq.queueUpdate}")
    private String queueNameUpdate;

    @Bean
    DirectExchange  exchangeUser(){
        return new DirectExchange(exchange);
    }

    @Bean
    Queue updateQueue(){
        return new Queue(queueNameUpdate);
    }

    @Bean
    public Binding bindingUpdate(final Queue updateQueue,final DirectExchange exchangeUser){
        return BindingBuilder.bind(updateQueue).to(exchangeUser).with(bindingKeyUpdate);
    }


}

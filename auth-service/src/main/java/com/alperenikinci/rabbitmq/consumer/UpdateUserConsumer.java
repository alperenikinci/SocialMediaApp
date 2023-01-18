package com.alperenikinci.rabbitmq.consumer;

import com.alperenikinci.mapper.AuthMapper;

import com.alperenikinci.rabbitmq.model.UpdateUserProfileModel;
import com.alperenikinci.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateUserConsumer {

private final AuthService authService;


   @RabbitListener(queues =("${rabbitmq.queueUpdate}") )
    public void newUserCreate(UpdateUserProfileModel model){
        log.info("User:{}",model.toString());
        authService.save(AuthMapper.INSTANCE.toUserAuth(model));
    }
}

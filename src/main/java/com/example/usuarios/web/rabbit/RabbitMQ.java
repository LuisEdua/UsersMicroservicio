package com.example.usuarios.web.rabbit;

import com.example.usuarios.utilities.MapperUtil;
import com.example.usuarios.web.dtos.responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQ implements IRabbitMQ{

    @Autowired
    private RabbitTemplate template;

    @Override
    public void sendToUsersSignUpResponseQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.users_sign_up_responses",serialize);
    }

    @Override
    public void sendToExceptionQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.users_errors", serialize);
    }

    @Override
    public void sendToUsersLogInResponseQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.users_log_in_response", serialize);
    }

    @Override
    public void sendToExceptionLogInQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.users_log_in_errors", serialize);
    }

    @Override
    public void sendToExceptionSignUpQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.users_sign_up_errors", serialize);
    }

}

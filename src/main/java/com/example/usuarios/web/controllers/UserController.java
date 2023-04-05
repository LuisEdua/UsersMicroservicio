package com.example.usuarios.web.controllers;

import com.example.usuarios.services.interfaces.IUserService;
import com.example.usuarios.utilities.MapperUtil;
import com.example.usuarios.web.dtos.requests.LogInRequest;
import com.example.usuarios.web.dtos.requests.SingUpRequest;
import com.example.usuarios.web.dtos.responses.BaseResponse;
import com.example.usuarios.web.rabbit.IRabbitMQ;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private IUserService service;

    @Autowired
    private IRabbitMQ rabbitMQ;

    @RabbitListener(queues = "queue.users_sign_up", errorHandler = "rabbitHandlerExceptions")
    public void signUp(String payload) throws JsonProcessingException{
        SingUpRequest request = MapperUtil.deserialize(payload, SingUpRequest.class);
        BaseResponse response = service.signUp(request);
        rabbitMQ.sendToUsersResponseQueue(response);
    }

    @RabbitListener(queues = "queue.users_log_in", errorHandler = "rabbitHandlerExceptions")
    public void logIn(String payload) throws JsonProcessingException{
        LogInRequest request = MapperUtil.deserialize(payload, LogInRequest.class);
        BaseResponse response = service.logIn(request);
        rabbitMQ.sendToUsersLogInResponseQueue(response);
    }

}

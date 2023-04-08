package com.example.usuarios.web.controllers;

import com.example.usuarios.services.interfaces.IUserService;
import com.example.usuarios.utilities.MapperUtil;
import com.example.usuarios.web.controllers.advices.UserExceptions;
import com.example.usuarios.web.dtos.requests.LogInRequest;
import com.example.usuarios.web.dtos.requests.SingUpRequest;
import com.example.usuarios.web.dtos.responses.BaseResponse;
import com.example.usuarios.web.rabbit.IRabbitMQ;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.security.auth.login.LoginException;

@Controller
public class UserController {
    @Autowired
    private IUserService service;

    @Autowired
    private IRabbitMQ rabbitMQ;

    @Autowired
    private UserExceptions exceptions;

    @RabbitListener(queues = "queue.users_sign_up")
    public void signUp(String payload) throws JsonProcessingException{
        SingUpRequest request = MapperUtil.deserialize(payload, SingUpRequest.class);
        try {
            BaseResponse response = service.signUp(request);
            rabbitMQ.sendToUsersSignUpResponseQueue(response);
        } catch (Exception e){
            exceptions.signUpError(e, request.getSessionId());
        }
    }

    @RabbitListener(queues = "queue.users_log_in")
    public void logIn(String payload) throws JsonProcessingException{
        LogInRequest request = MapperUtil.deserialize(payload, LogInRequest.class);
        try {
            BaseResponse response = service.logIn(request);
            rabbitMQ.sendToUsersLogInResponseQueue(response);
        }
        catch (Exception e){
            System.out.println("Usuario no valido");
            exceptions.logInError(e, request.getSessionId());
        }
    }

}

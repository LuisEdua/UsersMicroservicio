package com.example.usuarios.web.controllers.advices;

import com.example.usuarios.web.dtos.responses.BaseResponse;
import com.example.usuarios.web.rabbit.IRabbitMQ;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserExceptions {
    @Autowired
    private IRabbitMQ rabbitMQ;

    public Object logInError(Exception e, String sessionId) throws JsonProcessingException {
        System.out.println("Usuario no valido");
        BaseResponse response = BaseResponse.builder()
                .message(e.getMessage())
                .success(Boolean.FALSE)
                .httpStatus(HttpStatus.PRECONDITION_FAILED)
                .sessionId(sessionId)
                .statusCode(412)
                .data("Usuario no corresponde")
                .build();
        System.out.println("Todo bien");
        rabbitMQ.sendToExceptionLogInQueue(response);
        throw new AmqpRejectAndDontRequeueException("error");
    }

    public Object signUpError(Exception e, String sessionId) throws JsonProcessingException {
        BaseResponse response = BaseResponse.builder()
                .message(e.getMessage())
                .success(Boolean.FALSE)
                .httpStatus(HttpStatus.PRECONDITION_FAILED)
                .sessionId(sessionId)
                .statusCode(412)
                .data("Usuario existente")
                .build();
        rabbitMQ.sendToExceptionSignUpQueue(response);
        throw new AmqpRejectAndDontRequeueException("error");
    }
}

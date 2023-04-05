package com.example.usuarios.web.controllers.advices;

import com.example.usuarios.web.dtos.responses.BaseResponse;
import com.example.usuarios.web.rabbit.IRabbitMQ;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RabbitHandlerExceptions implements RabbitListenerErrorHandler {

    @Autowired
    private IRabbitMQ rabbitMQ;


    @Override
    public Object handleError(Message message, org.springframework.messaging.Message<?> message1, ListenerExecutionFailedException e) throws Exception {
        BaseResponse response = BaseResponse.builder()
                .message(e.getMessage())
                .success(Boolean.FALSE)
                .httpStatus(HttpStatus.PRECONDITION_FAILED)
                .statusCode(412)
                .build();
        rabbitMQ.sendToExceptionQueue(response);
        throw new AmqpRejectAndDontRequeueException("error");
    }
}

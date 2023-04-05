package com.example.usuarios.web.rabbit;

import com.example.usuarios.web.dtos.responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IRabbitMQ {
    void sendToUsersResponseQueue(BaseResponse response) throws JsonProcessingException;

    void sendToExceptionQueue(BaseResponse response) throws JsonProcessingException;

    void sendToUsersLogInResponseQueue(BaseResponse response) throws JsonProcessingException;
}

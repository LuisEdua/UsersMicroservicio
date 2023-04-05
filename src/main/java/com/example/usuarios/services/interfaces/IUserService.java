package com.example.usuarios.services.interfaces;

import com.example.usuarios.web.dtos.requests.LogInRequest;
import com.example.usuarios.web.dtos.requests.SingUpRequest;
import com.example.usuarios.web.dtos.responses.BaseResponse;

public interface IUserService{
    BaseResponse signUp(SingUpRequest request);

    BaseResponse logIn(LogInRequest request);
}

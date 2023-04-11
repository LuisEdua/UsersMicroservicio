package com.example.usuarios.services;

import com.example.usuarios.persistances.entities.User;
import com.example.usuarios.persistances.repositories.IUserRepository;
import com.example.usuarios.services.interfaces.IUserService;
import com.example.usuarios.web.controllers.advices.exceptions.InvalidCredentialsException;
import com.example.usuarios.web.dtos.requests.LogInRequest;
import com.example.usuarios.web.dtos.requests.SingUpRequest;
import com.example.usuarios.web.dtos.responses.BaseResponse;
import com.example.usuarios.web.dtos.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service @Validated
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public BaseResponse signUp(SingUpRequest request) {
        User user = repository.save(from(request));
        return BaseResponse.builder()
                .data(from(user))
                .message("The user was saved")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public BaseResponse logIn(LogInRequest request) {
        User user = repository.findByEmail(request.getEmail()).orElseThrow(InvalidCredentialsException::new);
        if(encoder.matches(request.getPassword(), user.getPassword())){
         return BaseResponse.builder()
                 .data(from(user))
                 .message("Verify success")
                 .success(Boolean.TRUE)
                 .httpStatus(HttpStatus.ACCEPTED)
                 .statusCode(HttpStatus.ACCEPTED.value())
                 .build();
        }
        throw new InvalidCredentialsException();
    }

    private UserResponse from(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        return response;
    }

    private User from(SingUpRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        return user;
    }
}

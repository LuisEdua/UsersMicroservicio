package com.example.usuarios.web.controllers.advices.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(){
        super("Invalid credentials, please use a valid email and password");
    }
}

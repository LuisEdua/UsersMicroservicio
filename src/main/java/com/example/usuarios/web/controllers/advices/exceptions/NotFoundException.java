package com.example.usuarios.web.controllers.advices.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){ super("The Entity was not found");}

}
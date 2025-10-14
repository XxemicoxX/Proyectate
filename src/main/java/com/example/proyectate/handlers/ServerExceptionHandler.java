package com.example.proyectate.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//falta logica
public class ServerExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleExceptionInternal() {
        return new ResponseEntity<>("Error del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

package com.example.springwebapp.controllers.advices;

import com.example.springwebapp.exceptionsData.IncorrectData;
import com.example.springwebapp.exceptionsData.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(UserAlreadyExistsException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}

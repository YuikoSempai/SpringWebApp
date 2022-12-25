package com.example.springwebapp.controllers.advices;

import com.example.springwebapp.exceptionsData.IncorrectData;
import com.example.springwebapp.exceptionsData.exceptions.DotException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(DotException exception){
        IncorrectData data = new IncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(Exception exception){
        IncorrectData data = new IncorrectData();
        if(exception.getMessage() == null){
            data.setInfo("Not valid json");
        }else {
            data.setInfo(exception.getMessage());
        }
        return new ResponseEntity<>(data,HttpStatus.BAD_REQUEST);
    }

}

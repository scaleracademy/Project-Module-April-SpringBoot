package com.scaler.springboot1.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskErrors {

    @ExceptionHandler
    ResponseEntity<Object> handleTasksExceptions(Exception e){
        return new ResponseEntity<>(e.getMessage().toString(),HttpStatus.NOT_FOUND);
    }
}

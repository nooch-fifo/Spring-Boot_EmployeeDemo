package com.example.EmployeeDemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class DefaultGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exc, WebRequest req){
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("TimeStamp: ", LocalDateTime.now());
        error.put("Exception: ", exc.getClass().getSimpleName());
        error.put("Error Message: ", exc.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}

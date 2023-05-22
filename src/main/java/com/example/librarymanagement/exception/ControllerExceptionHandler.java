package com.example.librarymanagement.exception;

import com.example.librarymanagement.dto.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> handleException(Exception exception) {
        return new ResponseEntity<>(
                Response.builder().status(400).message(exception.getMessage()).build()
        , HttpStatus.BAD_REQUEST);
    }
}

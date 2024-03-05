package com.project.productserviceapp.controllers;

import com.project.productserviceapp.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(InternalError.class)
    public ResponseEntity<String> InternalServerError(Exception exception) {
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> NotFound(Exception exception) {
        return new ResponseEntity<>("Not Found any record", HttpStatus.NOT_FOUND);
    }
}

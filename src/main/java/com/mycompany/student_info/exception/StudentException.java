package com.mycompany.student_info.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

@ControllerAdvice

public class StudentException{
    @ExceptionHandler
    public ResponseEntity<String> studentNotFoundException(NoSuchElementException ex) {
        return ResponseEntity.badRequest().body("Student not found" +ex.getMessage());
    }
}
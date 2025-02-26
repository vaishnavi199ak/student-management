package com.mycompany.student_info.exception;

import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice

public class StudentException{
    @ExceptionHandler
    public ResponseEntity<String> studentNotFoundException(NoSuchElementException ex) {
        return ResponseEntity.badRequest().body("Student not found" +ex.getMessage());
    }
}
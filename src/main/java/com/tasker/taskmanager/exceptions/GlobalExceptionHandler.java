package com.tasker.taskmanager.exceptions;

import com.tasker.taskmanager.dto.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> errorResponseResponseEntity(TaskNotFoundException ex){

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        ) ;

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> errorResponseResponseEntity(MethodArgumentNotValidException mx){

        String message = mx.getBindingResult()
                .getFieldError()
                .getField()
                + " "
                +
                mx.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage();

        ErrorResponse errorResponse = new ErrorResponse(
                message,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

       return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler (ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> errorResponseResponseEntity(ConstraintViolationException cx){



        String message = cx.getConstraintViolations()
                .iterator()
                .next()
                .getMessage();

        ErrorResponse errorResponse = new ErrorResponse(
                message,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

}
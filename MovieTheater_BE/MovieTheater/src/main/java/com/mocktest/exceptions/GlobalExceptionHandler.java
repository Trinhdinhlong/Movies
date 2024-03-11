package com.mocktest.exceptions;

import com.mocktest.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorResponse> handledException(BadRequestException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(exception.error)
                .message(exception.getMessage())
                .status("400")
                .timestamp(LocalDateTime.now())
                .path("/api/user")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    protected  ResponseEntity<ErrorResponse> handledException(NotFoundException exception){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .status("404")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ErrorResponse> handledException(AuthenticationException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .status("401")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(ex.getError())
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .status("400")
                .path("api/user")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

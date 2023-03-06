package com.sree.banking.transaction.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Sreenivasulu.Avula
 *
 */

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

    @ExceptionHandler(DatabaseException.class)
    public final ResponseEntity<ErrorResponse> handleDatabaseException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().toString(), 1011, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SendEmailNotificationException.class)
    public final ResponseEntity<ErrorResponse> handleSendEmailNotificationException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

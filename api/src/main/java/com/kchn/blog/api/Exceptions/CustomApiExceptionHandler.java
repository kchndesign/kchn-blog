package com.kchn.blog.api.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * This exception handler contains only one method that catches the custom ApiExceptions and returns correspondng Response Entities with a filled in ApiExceptionResponse
 * @author kevin
 *
 */
@ControllerAdvice
public class CustomApiExceptionHandler {
    
    @ExceptionHandler(CustomApiException.class) 
    public ResponseEntity<CustomApiExceptionResponse> handleCustomException( CustomApiException e ) {
        CustomApiExceptionResponse response = new CustomApiExceptionResponse(
            e.getStatus(),
            e.getMessage(), 
            e.getPayload());
        return new ResponseEntity<CustomApiExceptionResponse>(response, e.getStatus());
    }

}
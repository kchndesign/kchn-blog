package com.kchn.blog.api.Exceptions;

import org.springframework.http.HttpStatus;

/**
 * This is a schema the wraps the exception data (status, message, payload) into an object to be sent through a response entity. This is needed because throwing the ApiException itself (which extends a RuntimeException) will include a bunch of extra stuff.
 * @author kevin
 *
 */
public class CustomApiExceptionResponse {

    /**
     * HTTP response code (such as 404)
     */
    private HttpStatus status;
    
    /**
     * Plain English message to be sent to the user.
     */
    private String message;

    /**
     * Object to contain any relevant details about the error.
     * You can cast (String) or (Integer) into this if you want to include primitives. 
     */
    private Object payload;
    
    /*
     * Constructors
     */

    
    /**
     * Builds an ApiExceptionResponse object to be used as a response body upon throwing a custom exception.
     * @param status http response
     */
    public CustomApiExceptionResponse(HttpStatus status) {
        this.status = status;
    }

    
    /**
     * Builds an ApiExceptionResponse object to be used as a response body upon throwing a custom exception.
     * @param status http response
     * @param message plain english message
     */
    public CustomApiExceptionResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    
    
    /**
     * Builds an ApiExceptionResponse object to be used as a response body upon throwing a custom exception.
     * @param statuss http response
     * @param message plain english message
     * @param payload general purpose payload can be used to include any relevant data.
     */
    public CustomApiExceptionResponse(
        HttpStatus status, String message, Object payload) 
    {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    /*
     * Getters and setters
     */
    
    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getPayload() {
        return payload;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
    
}
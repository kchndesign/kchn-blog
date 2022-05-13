package com.kchn.blog.api.Exceptions;

import org.springframework.http.HttpStatus;

/**
 * Describes an exception message that will be sent to the user of the Api inside a ResponseEntity.
 * Write 'throw new ApiExceptionBuilder(..., ..., ...)'
 * @author kevin
 */
public class CustomApiException extends RuntimeException {
    
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
    
    
    /**
     * Default exception returns Http code 500 and "Internal Server Error"
     * No payload.
     */
    public CustomApiException() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = "Internal Server Error";
    }
    
    
    /**
     * Build new exception with just HttpStatus
     * @param status Http Status code.
     */
    public CustomApiException(HttpStatus status) {
        this.status = status;
    }
    
    
    /**
     * Build new exception with no object.
     * @param status Http Status code.
     * @param message Plain english message to be sent to the user.
     */
    public CustomApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }


    /**
     * Build new exception.
     * @param status Http Status code.
     * @param message Plain english message to be sent to the user.
     * @param payload Object that can contain any relevant details.
     */
    public CustomApiException(HttpStatus status, String message, Object payload) {
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
package com.cricket.packages.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;


public @Data class ExceptionResponse {

    private HttpStatus errorResponse;
    private String message;

    public HttpStatus getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(HttpStatus errorResponse) {
        this.errorResponse = errorResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionResponse(String message) {
        this.message = message;
    }
    public ExceptionResponse() {

    }
}

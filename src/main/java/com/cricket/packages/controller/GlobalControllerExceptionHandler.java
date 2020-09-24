package com.cricket.packages.controller;

import com.cricket.packages.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = {InvalidRegistrationDataException.class})
    ResponseEntity<ExceptionResponse> InvalidRegistrationException(Exception e) {
        return createResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidPredictDataException.class})
    ResponseEntity<ExceptionResponse> InvalidPredictDataException(Exception e) {
        return createResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PlayersFetchException.class})
    ResponseEntity<ExceptionResponse> PlayerFetchException(Exception e) {
        return createResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {MongoDBException.class})
    ResponseEntity MongoDBException(Exception e){
        return createResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {InvalidResetPasswordException.class})
    ResponseEntity<ExceptionResponse> InvalidResetPasswordException(Exception e) {
        return createResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PointsUpdationException.class})
    ResponseEntity<ExceptionResponse> PointsUpdationException(Exception e) {
        return createResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    ResponseEntity<ExceptionResponse> Exception(Exception e) {
        return createResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value ={InvalidPointsException.class})
    ResponseEntity<ExceptionResponse> InvalidPointsException(Exception e){
        return createResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidTokenException.class})
    ResponseEntity<ExceptionResponse> InvalidTokenException(Exception e) {
        return createResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {InvalidMatchScheduleException.class})
    ResponseEntity<ExceptionResponse> InvalidMatchScheduleException(Exception e) {
        return createResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity<ExceptionResponse> createResponseEntity(String message, HttpStatus httpStatus) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorResponse(httpStatus);
        error.setMessage(message);
        return new ResponseEntity<>(error, httpStatus);
    }

}

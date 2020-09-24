package com.cricket.packages.exception;

public class InvalidTokenException  extends Exception {
    public InvalidTokenException(String message){
        super(message);
    }
}
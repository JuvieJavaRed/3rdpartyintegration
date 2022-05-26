package com.apex.technicaltest.exceptions;

public class FailedLoginException extends RuntimeException{
    private String message;
    /**
     *parameterized constructor
     *@param message exception message
     */
    public FailedLoginException(String message){
        super(message);
    }
}

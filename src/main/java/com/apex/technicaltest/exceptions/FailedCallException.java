package com.apex.technicaltest.exceptions;

public class FailedCallException extends RuntimeException{
    private String message;
    /**
     *parameterized constructor
     *@param message exception message
     */
    public FailedCallException(String message){
        super(message);
    }
}

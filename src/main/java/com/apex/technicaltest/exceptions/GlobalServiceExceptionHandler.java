package com.apex.technicaltest.exceptions;

import com.apex.technicaltest.constants.LogConstants;
import com.apex.technicaltest.exceptions.FailedCallException;
import com.apex.technicaltest.exceptions.FailedLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FailedCallException.class)
    public ResponseEntity<?> handleFailedCallException(FailedCallException failedCallException){
        log.error(failedCallException.getMessage());
        return new ResponseEntity<>(failedCallException.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(FailedLoginException.class)
    public ResponseEntity<?> handleFailedLoginException(FailedLoginException failedLoginException){
        log.error(failedLoginException.getMessage());
        return new ResponseEntity<>(failedLoginException.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<?> handleNullPointerException(NullPointerException ex){
        String error = ex.getLocalizedMessage() == null? ex.toString() : ex.getLocalizedMessage();
        log.error("A null pointer exception has occured and becasuse :{}",ex.getMessage(),ex);
        return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
    }
}

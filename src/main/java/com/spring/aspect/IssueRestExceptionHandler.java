package com.spring.aspect;

import com.spring.rest.IssueErrorResponse;
import com.spring.rest.IssueNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IssueRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IssueErrorResponse> handleException(IssueNotFoundException e){
        // create issue error response
        IssueErrorResponse error = new IssueErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IssueErrorResponse> handleException(Exception e){
        IssueErrorResponse error = new IssueErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}

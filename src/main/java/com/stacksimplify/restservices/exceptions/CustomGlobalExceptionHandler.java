package com.stacksimplify.restservices.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //return super.handleMethodArgumentNotValid(ex, headers, status, request);
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "From method argument not valid ", ex.getMessage());
        return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), "GEH- Method not allowed  ", ex.getMessage());
        return new ResponseEntity<>(customErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),request.getDescription(true));
        return new ResponseEntity<>(customErrorDetails,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstrainViolationException(ConstraintViolationException ex , WebRequest request){
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex , WebRequest request){
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(), ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
    }
}
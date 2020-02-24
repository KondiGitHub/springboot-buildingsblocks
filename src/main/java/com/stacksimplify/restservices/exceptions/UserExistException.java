package com.stacksimplify.restservices.exceptions;

public class UserExistException extends Exception {

    public UserExistException(String message){
        super(message);
    }
}

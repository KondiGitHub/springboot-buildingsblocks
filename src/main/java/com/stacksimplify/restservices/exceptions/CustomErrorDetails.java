package com.stacksimplify.restservices.exceptions;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Getter
public class CustomErrorDetails {

    private Date timeStamp;
    private String message;
    private String errorDetails;

    public CustomErrorDetails(Date timeStamp, String message, String errorDetails){
        this.timeStamp = timeStamp;
        this.message = message;
        this.errorDetails = errorDetails;
    }

}

package com.stacksimplify.restservices.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class HelloWorld {
    private String firstName;
    private String lastName;
    private String city;

    public HelloWorld(String firstName, String lastName, String city){
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }


}

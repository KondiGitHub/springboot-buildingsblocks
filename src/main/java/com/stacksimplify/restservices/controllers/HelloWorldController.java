package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.model.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {


    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public HelloWorld helloWorld(){
        return new HelloWorld("Venkat","Mailaram","Dayton");
    }
}

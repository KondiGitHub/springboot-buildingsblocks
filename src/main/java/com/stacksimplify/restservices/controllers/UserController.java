package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.model.User;
import com.stacksimplify.restservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String helloWorld(){
       return "hello";
    }

    @RequestMapping(value = "users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers(){
        List<User> users = userService.getAllUsers();
       return users;
    }

    @PostMapping("/user")
    public User createUser( @RequestBody User user){
        return userService.createUser(user);
    }
}

package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.model.User;
import com.stacksimplify.restservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("users/{id}")
    public Optional<User> getUser(@PathVariable("id") Long id){
       return  userService.findById(id);
    }

    //@RequestMapping(value = "users/{id}", method = RequestMethod.PUT)
    @PutMapping("users/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user){

        return userService.updateUserById(id, user);

    }

    @DeleteMapping("users/{id}")
    public String deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return  id + " deleted ";
    }

    @GetMapping("users/userByName/{name}")
    public User getUser(@PathVariable("name") String name){
        return  userService.findByName(name);
    }
}

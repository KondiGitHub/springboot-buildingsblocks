package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.exceptions.UserExistException;
import com.stacksimplify.restservices.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.model.Order;
import com.stacksimplify.restservices.model.User;
import com.stacksimplify.restservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String helloWorld(){
       return "hello";
    }

   @GetMapping()
    public List<User> getAllUsers(){
        List<User> users = userService.getAllUsers();
       return users;
    }

    @PostMapping()
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder){

        try {
            userService.createUser(user);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(builder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<Void>(httpHeaders,HttpStatus.CREATED);
        } catch (UserExistException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@Min (1) @PathVariable("id") Long id){
        try {
            return  userService.findById(id);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //@RequestMapping(value = "users/{id}", method = RequestMethod.PUT)
    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user){

        try {
            return userService.updateUserById(id, user);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return  id + " deleted ";
    }

    @GetMapping("/userByName/{name}")
    public User getUser(@PathVariable("name") String name){
        User user = null;
        try {
            user = userService.findByName(name);
        } catch (UserNameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return user;
    }

//    @GetMapping("/{id}/orders")
//    public List<Order> getAllOrders(@PathVariable("id") Long id){
//        try {
//           return userService.getAllOrders(id);
//        } catch (UserNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
}

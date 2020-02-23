package com.stacksimplify.restservices.service;

import com.stacksimplify.restservices.model.User;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<User> getAllUsers() {
       return  userRepository.findAll();
    }

    public User createUser( User user){
        return userRepository.save(user);
    }
}

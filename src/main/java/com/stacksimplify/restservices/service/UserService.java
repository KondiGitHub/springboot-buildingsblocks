package com.stacksimplify.restservices.service;

import com.stacksimplify.restservices.model.User;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public User updateUserById(Long id, User user){
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        if (userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }

    }

    public User findByName(String name){
        return userRepository.findByUserName(name);
    }
}

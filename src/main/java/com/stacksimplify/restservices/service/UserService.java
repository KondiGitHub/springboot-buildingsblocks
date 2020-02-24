package com.stacksimplify.restservices.service;

import com.stacksimplify.restservices.exceptions.UserExistException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.model.User;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<User> getAllUsers() {
       return  userRepository.findAll();
    }

    public User createUser( User user) throws UserExistException {
        if (userRepository.findByUserName(user.getUserName())!=null){
            throw new UserExistException("User already exist in repository");
        }
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(! user.isPresent()){
            throw new UserNotFoundException(" User Id:  " + id + " not found in user Repository ");
        }
        return user;
    }

    public User updateUserById(Long id, User user) throws UserNotFoundException {
        if (!userRepository.findById(id).isPresent()){
            throw new UserNotFoundException(" User Id:  " + id + " not found in user Repository ");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        if (!userRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " User Id:  " + id + " not found in user Repository ");
        }
        userRepository.deleteById(id);
    }

    public User findByName(String name){
        return userRepository.findByUserName(name);
    }
}

package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.model.Order;
import com.stacksimplify.restservices.model.User;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.service.OrderService;
import com.stacksimplify.restservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class OrderHateoasController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{userId}/orders")
    public CollectionModel<Order>  getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
        Optional<User> user = userService.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("User Not found");
        }
        CollectionModel<Order> collectionModel = new CollectionModel<Order>(user.get().getOrders());
        return collectionModel;
    }
}

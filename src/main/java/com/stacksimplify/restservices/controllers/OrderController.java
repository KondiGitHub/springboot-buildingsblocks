package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserExistException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.model.Order;
import com.stacksimplify.restservices.model.User;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class OrderController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{userId}/orders")
    public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("User Not found");
        }
       return  user.get().getOrders();
    }

    @PostMapping("/{userId}/orders")
    public Order createOrder(@PathVariable("userId") Long userId, @Valid @RequestBody Order order, UriComponentsBuilder builder) throws UserNotFoundException {

        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User Not found");
        }

        User user = optionalUser.get();
        order.setUser(user);
        return orderService.createOrder(order);
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public Order getAllOrders(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId) throws UserNotFoundException, OrderNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("User Not found");
        }

        Optional<Order> optionalOrder = user.get().getOrders().stream().filter(order -> order.getOrderId().equals(orderId)).findAny();
        if(!optionalOrder.isPresent()){
            throw new OrderNotFoundException("Order not found");
        }
        return optionalOrder.get();
    }
}

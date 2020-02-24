package com.stacksimplify.restservices.service;

import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.model.Order;
import com.stacksimplify.restservices.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order) ;
    }

    public Order findOrder(Long id) throws OrderNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(!optionalOrder.isPresent()){
            throw new OrderNotFoundException("Order Not Found");
        }
        return optionalOrder.get();
    }
}

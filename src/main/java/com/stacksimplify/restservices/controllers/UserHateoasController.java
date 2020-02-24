package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.model.Order;
import com.stacksimplify.restservices.model.User;
import com.stacksimplify.restservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {
    @Autowired
    UserService userService;

    @GetMapping()
    public CollectionModel<User> getAllUsers(){
        List<User> users = userService.getAllUsers();
        for(User user: users) {
             Long userId = user.getId();
            Link selfLink = linkTo(this.getClass()).slash(userId).withSelfRel();
            user.add(selfLink);
           try {
               CollectionModel<Order> orderCollectionModel = methodOn(OrderHateoasController.class).getAllOrders(userId);
               Link link = linkTo(orderCollectionModel).withRel("all-orders");
               user.add(link);
           } catch (UserNotFoundException e) {
               e.printStackTrace();
           }
        }
        Link link = linkTo(this.getClass()).withSelfRel();
        CollectionModel<User> userCollectionModel = new CollectionModel<User>(users,link);
        return userCollectionModel;
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUser(@Min(1) @PathVariable("id") Long id){
        try {
            Optional<User> optionalUser = userService.findById(id);
            User user = optionalUser.get();
            Long userId = user.getId();
            Link selfLink = linkTo(this.getClass()).slash(userId).withSelfRel();
            user.add(selfLink);
            EntityModel<User> entityModel = new EntityModel<>(user);
            return entityModel;

        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

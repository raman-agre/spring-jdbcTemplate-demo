package com.example.jdbcTemplate.controller;

import com.example.jdbcTemplate.entity.User;
import com.example.jdbcTemplate.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    UserRepository repository;

    public UserController(UserRepository repository){
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return repository.getUserDetails(id);
    }

    @GetMapping("/get/{id}")
    public User getUser2(@PathVariable int id){
        return repository.getUserDetails(id);
    }


}

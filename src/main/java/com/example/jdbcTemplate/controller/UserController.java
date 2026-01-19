package com.example.jdbcTemplate.controller;

import com.example.jdbcTemplate.entity.User;
import com.example.jdbcTemplate.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.MaskFormatter;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/get/all")
    public List<User> getUserList(){
        return repository.getUserList();
    }

    @PostMapping("/add")
    public int addUser(@RequestBody User user){
        return repository.saveByNamedParameter(user);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteUser(@PathVariable int id){
        return repository.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    public int updateUser(@PathVariable int id, @RequestParam String city){
        return repository.update(id, city);
    }

}

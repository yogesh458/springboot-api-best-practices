package com.example.demo.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserReturn;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping
    public Page<UserReturn> getUser(@RequestParam(defaultValue="0") int page,@RequestParam (defaultValue = "5") int size){
 
        return userService.getAllUsers(page, size);
                                
    }

    @PostMapping
    public UserReturn addUser(@Valid @RequestBody UserRequest request) {
        return userService.addUser(request);
    }

    @GetMapping("/{id}")
    public UserReturn getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
    }


}

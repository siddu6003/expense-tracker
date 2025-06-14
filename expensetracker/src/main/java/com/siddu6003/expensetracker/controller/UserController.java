package com.siddu6003.expensetracker.controller;

import com.siddu6003.expensetracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.siddu6003.expensetracker.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String heartBeat() {
        return "i'm alive";
    }
    @PostMapping("/register")
    public String createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
}

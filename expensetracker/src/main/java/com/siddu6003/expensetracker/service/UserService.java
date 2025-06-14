package com.siddu6003.expensetracker.service;


import com.siddu6003.expensetracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.siddu6003.expensetracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    public String registerUser(User user) {
        String hashed = passwordEncoder.encode(user.getPassword());
        int result = userRepository.registerUser(user.getName(), user.getUsername(), hashed);
        return result != -1 ? "successfully created the user" : "failed to create the user";
    }
}

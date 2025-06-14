package com.siddu6003.expensetracker.common;

import com.siddu6003.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserContext {

    @Autowired
    UserRepository userRepository;

    public Long getUserId() {
        return userRepository.getUserIdByUsername(getLoggedInUsername());
    }

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // This returns the username
    }
}

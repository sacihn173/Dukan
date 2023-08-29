package com.dukan.app.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRpo;

    public User findUserById(int userId) {
        return userRpo.findById(userId).orElse(null);
    }

    public User save(User user) {
        return userRpo.save(user);
    }

}

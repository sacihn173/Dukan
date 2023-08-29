package com.dukan.app.User;

import com.dukan.app.Testing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRpo;

    @Autowired
    private Testing testing;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        testing.test();
        return ResponseEntity.ok(new User());
//        return ResponseEntity.ok(userRpo.save(user));
    }

}

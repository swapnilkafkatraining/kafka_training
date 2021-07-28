package com.example.SimpleUserApp.controller;

import com.example.SimpleUserApp.model.User;
import com.example.SimpleUserApp.service.UserService;
import com.fasterxml.jackson.databind.type.ClassStack;
import com.sun.media.sound.SF2InstrumentRegion;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.IconUIResource;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Integer userId) {
        UserService userService = new UserService();
            return userService.getUserById(userId);
    }

    @RequestMapping("/")
    public List getAllUsers() {
        UserService userService = new UserService();
        return userService.getAllUsers();
    }
}

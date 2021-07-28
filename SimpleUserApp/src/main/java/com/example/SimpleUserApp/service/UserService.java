package com.example.SimpleUserApp.service;

import com.example.SimpleUserApp.model.User;

import java.util.Arrays;
import java.util.List;

public class UserService {

    User user1 = new User(1, "Swapnil", "Garg", "India");
    User user2 = new User(2, "John", "Dheere", "US");
    User user3 = new User(3, "Ashfaq", "Hussain", "Pakistan");

    List<User> users = Arrays.asList(user1, user2, user3);

     public List getAllUsers() {
         return users;
     }

    public User getUserById(Integer userId) {
        User user = users.stream()
                .filter(t -> userId.equals(t.getUserId()))
                .findFirst()
                .orElse(null);
        return user;
    }
}

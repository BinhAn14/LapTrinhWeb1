package com.example.demo20.controller;

import com.example.demo20.model.User;
import com.example.demo20.repository.UserRepository;
import com.example.demo20.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {

   @Autowired
   private UserService userService;


   public UserController() {
   }



   @GetMapping("/users")
   @ResponseBody
   public List<User> getUserList() {
       return userService.findAll();
   }


   @GetMapping("/users/{id}")  
   public ResponseEntity<User> getUserById(@PathVariable("id") int userId) {
    for (User user : userService.findAll()) {
        if (user.getId() == userId) {
            return ResponseEntity.status(200).body(user);
        }
    }
    return ResponseEntity.status(404).body(null);  
}


@DeleteMapping("/users/{id}")
@ResponseBody
public List<User> removeUserById(@PathVariable("id") Long userId) {
    userService.delete(userId);
    return  userService.findAll();
}


@PostMapping("/user")
public ResponseEntity<User> createUser(@RequestBody User user) {
    userService.save(user);
    return ResponseEntity.status(201).body(user);
}


@PutMapping("/user/{id}")
public ResponseEntity<User> udpateUser(@PathVariable("id") Long userId, @RequestBody User updateUser){
    User user = userService.findById(userId);
    if (user != null) {
        user.setName(updateUser.getName());
        user.setEmail(updateUser.getEmail());
        userService.save(user);
        return ResponseEntity.status(200).body(user);
    }
    return ResponseEntity.status(404).body(null);
}
}

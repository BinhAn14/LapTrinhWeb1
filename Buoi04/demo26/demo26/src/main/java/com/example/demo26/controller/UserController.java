package com.example.demo26.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/users")
    public String usersPage(Model model) {
        List<User> users = Arrays.asList(
            new User("An", "admin"),
            new User("Dũng", "editor"),
            new User("Phúc", "author"),
            new User("Chung", "user")
        );
        model.addAttribute("users", users);
        return "users"; // Tên view là 'users.html'
    }
}


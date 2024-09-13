package com.example.demo23.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/link")
    public String linkExpression(Model model) {
        model.addAttribute("dynamiclink", "products");
        return "link";
    }
}

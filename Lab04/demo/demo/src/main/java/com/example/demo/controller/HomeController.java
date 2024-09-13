package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
@RequestMapping("home")
public class HomeController {

    @GetMapping("/text")
    public String HomeText(Model model){
        System.out.println("Hello IT22A");
        String name = "Hello St22A";
        model.addAttribute("namehtml", name);
        return "index";
    }
}

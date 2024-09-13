package com.example.demo24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/about")
    public String aboutPage(Model model) {
        // Bạn có thể thêm bất kỳ thuộc tính nào vào model nếu cần
        return "about"; // Tên view là 'about.html'
    }
}


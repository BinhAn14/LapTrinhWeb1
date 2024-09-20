package com.example.demo27.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    private CountryRepository repo; // Repository để lấy danh sách các quốc gia

    @GetMapping
    public String showTravelForm(Model model) {
        // Lấy danh sách các quốc gia từ repository
        List<Country> countries = repo.getCountries();
        
        // Kiểm tra nếu danh sách countries không phải là null
        if (countries == null) {
            countries = new ArrayList<>();
        }
        
        model.addAttribute("countries", countries); // Thêm danh sách các quốc gia vào model
        
        // Lấy các loại hình du lịch
        model.addAttribute("travelTypes", TravelType.values()); // Thêm các loại hình du lịch vào model
        
        // Tạo đối tượng TravelRequest trống và thêm vào model
        model.addAttribute("travelRequest", new TravelRequest());
        
        return "travel"; // Trả về view tên là travel
    }
    
}

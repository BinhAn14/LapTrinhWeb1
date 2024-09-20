package com.example.demo25.controller;

import com.example.demo25.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class PersonController {

    @GetMapping("/people")
    public String listPeople(Model model) {
        // Tạo danh sách các đối tượng Person
        List<Person> people = Arrays.asList(
            new Person("Alice", "female"),
            new Person("Bob", "male"),
            new Person("Carol", "female"),
            new Person("Dave", "male")
        );

        // Thêm danh sách người vào mô hình
        model.addAttribute("people", people);

        // Trả về tên của template
        return "people";
    }
}

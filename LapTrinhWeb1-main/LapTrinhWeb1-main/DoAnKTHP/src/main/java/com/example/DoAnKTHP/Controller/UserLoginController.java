package com.example.DoAnKTHP.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DoAnKTHP.models.Timetable;
import com.example.DoAnKTHP.models.UserGV;
import com.example.DoAnKTHP.service.UserGVService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserLoginController {

    @Autowired
    private UserGVService userGVService;

    @GetMapping("/signup")
    public String showSignup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password, @RequestParam String email,
                         ModelMap model) {
        UserGV existingUser = userGVService.findUserByUsername(username);
        if (existingUser != null) {
            model.addAttribute("message", "Tên đăng nhập đã tồn tại!");
            return "signup";
        }

        UserGV newUser = new UserGV();
        newUser.setUserName(username);
        newUser.setPassWord(password);
        newUser.setEmail(email);
        userGVService.saveUser(newUser);

        model.addAttribute("successMessage", "Đăng ký thành công! Vui lòng đăng nhập.");
        return "redirect:/login";
    }

    @GetMapping("/")
    public String showLogin(ModelMap model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, ModelMap model,
                        HttpSession session) {
        UserGV user = userGVService.findUserByUsername(username);
        if (user == null || !user.getPassWord().equals(password)) {
            model.addAttribute("ERROR", "Username hoặc Password không chính xác");
            return "login";
        }
        session.setAttribute("USERS", user);
        return "redirect:/select-class";
    }
    @GetMapping("/select-class")
    public String selectClass(ModelMap model) {
       
        return "select-class";  
    }

 @GetMapping("/profile")
public String showProfile(HttpSession session, ModelMap model) {
    UserGV user = (UserGV) session.getAttribute("USERS");
    if (user == null) {
        return "redirect:/";  
    }
    
    List<Timetable> teachingSchedule = userGVService.findTeachingScheduleByTeacherName(user.getUserName());
    model.addAttribute("user", user);
    model.addAttribute("teachingSchedule", teachingSchedule);
    
    return "profile";  
}

    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("USERS");
        return "redirect:/login";
    }
    
}

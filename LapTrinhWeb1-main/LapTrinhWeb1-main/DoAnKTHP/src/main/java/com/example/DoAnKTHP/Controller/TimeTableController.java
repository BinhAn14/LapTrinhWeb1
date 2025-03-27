package com.example.DoAnKTHP.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DoAnKTHP.models.Timetable;
import com.example.DoAnKTHP.service.TimetableService;
import com.example.DoAnKTHP.service.UserGVService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class TimeTableController {

    @Autowired
    private TimetableService timetableService;

    @GetMapping("/timetable")
    public String getTimetable(
            @RequestParam(required = false) String subjectName,
            @RequestParam(required = false) String className,
            @RequestParam(required = false) String room,
            Model model) {

        List<Timetable> timetable = timetableService.getTimetableByCriteria(subjectName, className, room);
        
        model.addAttribute("subjectName", subjectName);
        model.addAttribute("className", className);
        model.addAttribute("room", room);
        model.addAttribute("timetable", timetable);
        
        return "timetable"; 
    }
    @Autowired
       private UserGVService userGVService;

       @GetMapping("/available-schedule")
       public String showAvailableSchedule(
           @RequestParam(required = false) String className, 
           Model model) {
   
           List<Timetable> availableSchedules = new ArrayList<>();
           if (className != null && !className.isEmpty()) {
               availableSchedules = userGVService.findAvailableSchedulesForClass(className);
           } else {
               availableSchedules = userGVService.findAvailableSchedules();
           }
   
           // Sắp xếp danh sách lịch theo thứ tự ngày trong tuần (Thứ Hai đến Chủ Nhật)
           availableSchedules.sort(Comparator.comparingInt(schedule -> getDayOfWeekNumber(schedule.getDayOfWeek())));
   
           model.addAttribute("availableSchedules", availableSchedules);
           model.addAttribute("className", className);
   
           return "available-schedule"; 
       }
   
       private int getDayOfWeekNumber(String dayOfWeek) {
           switch (dayOfWeek) {
               case "Thứ 2":
                   return 1;
               case "Thứ 3":
                   return 2;
               case "Thứ 4":
                   return 3;
               case "Thứ 5":
                   return 4;
               case "Thứ 6":
                   return 5;
               case "Thứ 7":
                   return 6;
               case "Chủ Nhật":
                   return 7;
               default:
                   return 0; 
           }
       }

           
       

    @PostMapping("/register-teaching")
public String registerTeaching(@RequestParam Long classId, Model model) {
   
    model.addAttribute("message", "Đăng ký dạy thành công!");
    return "redirect:/available-schedule";
}
}
    
package com.example.DoAnKTHP.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DoAnKTHP.Repository.TimetableRepository;
import com.example.DoAnKTHP.Repository.UserGVRepository;
import com.example.DoAnKTHP.models.Timetable;
import com.example.DoAnKTHP.models.UserGV;

@Service
public class UserGVService {

    @Autowired
    private UserGVRepository userGVRepository;

    public UserGV findUserByUsername(String username) {
        return userGVRepository.findUserGVByUserName(username);
    }

    public void saveUser(UserGV user) {
        userGVRepository.save(user);
    }
    public List<UserGV> findAllTeachers() {
        return userGVRepository.findAll(); // Hoặc bạn có thể thực hiện lọc để chỉ lấy giảng viên
    }
      @Autowired
    private TimetableRepository timetableRepository;

    // Phương thức tìm lịch dạy của giáo viên theo tên
    public List<Timetable> findTeachingScheduleByTeacherName(String teacherName) {
        return timetableRepository.findByTeacherName(teacherName);
    }

    public List<Timetable> findAvailableSchedules() {
        List<Timetable> allSchedules = timetableRepository.findAll();  // Lấy tất cả các lịch
        List<Timetable> availableSchedules = new ArrayList<>();

        // Các ngày trong tuần và ca học
        String[] daysOfWeek = {"Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6"};
        String[] sessions = {"Sáng", "Chiều"};

        // Kiểm tra tất cả các phòng
        Set<String> allRooms = allSchedules.stream()
                                           .map(Timetable::getRoom)
                                           .collect(Collectors.toSet());

        for (String room : allRooms) {
            for (String day : daysOfWeek) {
                for (String session : sessions) {
                    boolean isAvailable = true;
                    for (Timetable timetable : allSchedules) {
                        // Kiểm tra lịch đã có giảng viên hoặc phòng đã có lịch
                        if (timetable.getRoom().equals(room) &&
                            timetable.getDayOfWeek().equals(day) &&
                            timetable.getSession().equals(session) &&
                            timetable.getTeacherName() != null &&
                            !timetable.getTeacherName().isEmpty()) {
                            isAvailable = false;
                            break;
                        }
                    }

                    // Nếu phòng trống, thêm vào danh sách lịch trống
                    if (isAvailable) {
                        Timetable availableSchedule = new Timetable();
                        availableSchedule.setRoom(room);
                        availableSchedule.setDayOfWeek(day);
                        availableSchedule.setSession(session);
                        availableSchedule.setSubjectName("Chưa có môn học");
                        availableSchedules.add(availableSchedule);
                    }
                }
            }
        }

        return availableSchedules;
    }

    // Phương thức tìm lịch trống cho lớp học cụ thể
    public List<Timetable> findAvailableSchedulesForClass(String className) {
        List<Timetable> allSchedules = timetableRepository.findAll();  // Lấy tất cả các lịch
        List<Timetable> availableSchedules = new ArrayList<>();

        String[] daysOfWeek = {"Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6"};
        String[] sessions = {"Sáng", "Chiều"};

        // Lọc lịch trống theo lớp học
        Set<String> allRooms = allSchedules.stream()
                                           .filter(t -> t.getClassName().equalsIgnoreCase(className))  // Lọc theo tên lớp
                                           .map(Timetable::getRoom)
                                           .collect(Collectors.toSet());

        for (String room : allRooms) {
            for (String day : daysOfWeek) {
                for (String session : sessions) {
                    boolean isAvailable = true;
                    for (Timetable timetable : allSchedules) {
                        if (timetable.getRoom().equals(room) &&
                            timetable.getDayOfWeek().equals(day) &&
                            timetable.getSession().equals(session) &&
                            timetable.getTeacherName() != null &&
                            !timetable.getTeacherName().isEmpty()) {
                            isAvailable = false;
                            break;
                        }
                    }

                    if (isAvailable) {
                        Timetable availableSchedule = new Timetable();
                        availableSchedule.setRoom(room);
                        availableSchedule.setDayOfWeek(day);
                        availableSchedule.setSession(session);
                        availableSchedule.setSubjectName("Chưa có môn học");
                        availableSchedules.add(availableSchedule);
                    }
                }
            }
        }

        return availableSchedules;
    }
    

    
}

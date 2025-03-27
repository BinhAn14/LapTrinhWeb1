package com.example.DoAnKTHP.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.DoAnKTHP.models.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    List<Timetable> findByTeacherName(String teacherName);
    List<Timetable> findBySubjectNameContainingIgnoreCase(String subjectName);
    List<Timetable> findByClassNameContainingIgnoreCase(String className);
    List<Timetable> findByRoomContainingIgnoreCase(String room);
    List<Timetable> findBySubjectNameContainingIgnoreCaseAndClassNameContainingIgnoreCaseAndRoomContainingIgnoreCase(String subjectName, String className, String room);
    List<Timetable> findByDayOfWeekAndSessionAndRoom(String dayOfWeek, String session, String room);
  
}

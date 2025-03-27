package com.example.DoAnKTHP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DoAnKTHP.Repository.TimetableRepository;
import com.example.DoAnKTHP.models.Timetable;

import java.util.List;

@Service
public class TimetableService {

    @Autowired
    private TimetableRepository timetableRepository;

    public List<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }

    public List<Timetable> getTimetableByCriteria(String subjectName, String className, String room) {
        if (subjectName != null && className != null && room != null) {
            return timetableRepository.findBySubjectNameContainingIgnoreCaseAndClassNameContainingIgnoreCaseAndRoomContainingIgnoreCase(subjectName, className, room);
        } else if (subjectName != null) {
            return timetableRepository.findBySubjectNameContainingIgnoreCase(subjectName);
        } else if (className != null) {
            return timetableRepository.findByClassNameContainingIgnoreCase(className);
        } else if (room != null) {
            return timetableRepository.findByRoomContainingIgnoreCase(room);
        }
        return timetableRepository.findAll();
    }
    
}

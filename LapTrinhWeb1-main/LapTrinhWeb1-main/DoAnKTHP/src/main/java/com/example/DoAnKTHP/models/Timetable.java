package com.example.DoAnKTHP.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Timetable {

    @Id
    private Long id;

    private String dayOfWeek;        // Thứ trong tuần
    private String session;           // Buổi học (Sáng, Chiều, Tối)
    private String periods;           // Tiết học (Ví dụ: Tiết 1-3, Tiết 4-6)
    private String subjectName;       // Tên học phần
    private String room;              // Phòng
    private String className;         // Lớp
    private String startDate;         // Ngày bắt đầu
    private String endDate;           // Ngày kết thúc
    private String teacherName;       // Tên giáo viên

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}

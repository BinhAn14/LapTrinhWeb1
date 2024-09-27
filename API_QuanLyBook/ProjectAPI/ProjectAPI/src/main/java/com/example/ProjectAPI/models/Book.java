package com.example.ProjectAPI.models;

public class Book {
    private String id;
    private String title;
    private String author;
    private String nhaXB;  

    public Book(String id, String title, String author, String nhaXB) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.nhaXB = nhaXB;
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }
}


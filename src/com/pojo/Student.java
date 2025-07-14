package com.pojo;

public class Student {
    private String username;
    private String password;
    private String name;
    private String email;
    private int rollNo;
    private String course;

    public Student() {}

    public Student(String username, String password, String name, String email, int rollNo, String course) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.rollNo = rollNo;
        this.course = course;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getRollNo() { return rollNo; }
    public void setRollNo(int rollNo) { this.rollNo = rollNo; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
}

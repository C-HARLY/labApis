package com.lab.demo.model;

public class Student {
    private int id;
    private String name;
    private String lastName;
    private String major;
    private int age;

    public Student() {}

    public Student(int id, String name, String lastName, String major, int age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.major = major;
        this.age = age;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
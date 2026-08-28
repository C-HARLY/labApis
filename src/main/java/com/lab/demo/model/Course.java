package com.lab.demo.model;

public class Course {
    private int id;
    private String name;
    private String description;
    private int credits;
    private String modality;

    public Course() {}

    public Course(int id, String name, String description, int credits, String modality) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.modality = modality;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public String getModality() { return modality; }
    public void setModality(String modality) { this.modality = modality; }
}
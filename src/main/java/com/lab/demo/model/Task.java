package com.lab.demo.model;

public class Task {
    private int id;
    private String title;
    private String description;
    private String priority;
    private Boolean completed; // Usamos Boolean (clase envoltura) para poder validar si es null

    public Task() {}

    public Task(int id, String title, String description, String priority, Boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }
}
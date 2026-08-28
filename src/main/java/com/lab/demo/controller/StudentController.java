package com.lab.demo.controller;

import com.lab.demo.model.Student;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(1, "Carlos", "Perez", "Ingeniería en Sistemas", 21));
        students.add(new Student(2, "Laura", "Gomez", "Medicina", 22));
        students.add(new Student(3, "Diana", "Lopez", "Arquitectura", 20));
        students.add(new Student(4, "Juan", "Martinez", "Derecho", 23));
        students.add(new Student(5, "Ana", "Ruiz", "Diseño Gráfico", 19));
    }

    @GetMapping
    public List<Student> getAll() { return students; }

    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Student create(@RequestBody Student newStudent) {
        students.add(newStudent);
        return newStudent;
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable int id, @RequestBody Student updated) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                updated.setId(id);
                students.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Student patch(@PathVariable int id, @RequestBody Student partial) {
        for (Student s : students) {
            if (s.getId() == id) {
                if (partial.getName() != null) s.setName(partial.getName());
                if (partial.getLastName() != null) s.setLastName(partial.getLastName());
                if (partial.getMajor() != null) s.setMajor(partial.getMajor());
                if (partial.getAge() != 0) s.setAge(partial.getAge());
                return s;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        students.removeIf(s -> s.getId() == id);
        return "Estudiante con ID " + id + " eliminado.";
    }
}
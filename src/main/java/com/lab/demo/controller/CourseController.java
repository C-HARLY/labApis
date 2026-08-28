package com.lab.demo.controller;

import com.lab.demo.model.Course;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CourseController {

    private List<Course> courses = new ArrayList<>();

    public CourseController() {
        courses.add(new Course(1, "Programación III", "Desarrollo de APIs REST", 5, "Virtual"));
        courses.add(new Course(2, "Bases de Datos I", "Modelado relacional y SQL", 4, "Presencial"));
        courses.add(new Course(3, "Ciberseguridad", "Fundamentos de Red Team y Blue Team", 5, "Virtual"));
        courses.add(new Course(4, "Redes de Computadoras", "Modelo OSI y TCP/IP", 4, "Híbrido"));
        courses.add(new Course(5, "Estadística", "Probabilidad y distribuciones", 3, "Presencial"));
    }

    @GetMapping
    public List<Course> getAll() { return courses; }

    @GetMapping("/{id}")
    public Course getById(@PathVariable int id) {
        return courses.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Course create(@RequestBody Course newCourse) {
        courses.add(newCourse);
        return newCourse;
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable int id, @RequestBody Course updated) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                updated.setId(id);
                courses.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Course patch(@PathVariable int id, @RequestBody Course partial) {
        for (Course c : courses) {
            if (c.getId() == id) {
                if (partial.getName() != null) c.setName(partial.getName());
                if (partial.getDescription() != null) c.setDescription(partial.getDescription());
                if (partial.getCredits() != 0) c.setCredits(partial.getCredits());
                if (partial.getModality() != null) c.setModality(partial.getModality());
                return c;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        courses.removeIf(c -> c.getId() == id);
        return "Curso con ID " + id + " eliminado.";
    }
}
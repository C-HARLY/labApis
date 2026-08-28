package com.lab.demo.controller;

import com.lab.demo.model.Task;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new Task(1, "Estudiar Java", "Repasar Spring Boot", "Alta", true));
        tasks.add(new Task(2, "Laboratorio V", "Terminar las 10 APIs REST", "Alta", false));
        tasks.add(new Task(3, "Ir al gimnasio", "Rutina de piernas", "Media", false));
        tasks.add(new Task(4, "Comprar croquetas", "Comida para el chihuahua", "Baja", false));
        tasks.add(new Task(5, "Repasar Linux", "Comandos básicos de Ubuntu", "Media", true));
    }

    @GetMapping
    public List<Task> getAll() { return tasks; }

    @GetMapping("/{id}")
    public Task getById(@PathVariable int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Task create(@RequestBody Task newTask) {
        tasks.add(newTask);
        return newTask;
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable int id, @RequestBody Task updated) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                updated.setId(id);
                tasks.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    // Ejemplo específico de PATCH que pide tu rúbrica
    @PatchMapping("/{id}")
    public Task patch(@PathVariable int id, @RequestBody Task partial) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                if (partial.getTitle() != null) t.setTitle(partial.getTitle());
                if (partial.getDescription() != null) t.setDescription(partial.getDescription());
                if (partial.getPriority() != null) t.setPriority(partial.getPriority());
                if (partial.getCompleted() != null) t.setCompleted(partial.getCompleted());
                return t;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        tasks.removeIf(t -> t.getId() == id);
        return "Tarea eliminada.";
    }
}
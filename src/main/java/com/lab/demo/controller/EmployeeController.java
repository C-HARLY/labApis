package com.lab.demo.controller;

import com.lab.demo.model.Employee;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        employees.add(new Employee(1, "Mario", "Gerente", 5000.00, "Ventas"));
        employees.add(new Employee(2, "Sofia", "Desarrollador Junior", 2500.00, "IT"));
        employees.add(new Employee(3, "Luis", "Analista SOC", 3000.00, "Ciberseguridad"));
        employees.add(new Employee(4, "Carmen", "Contadora", 2800.00, "Finanzas"));
        employees.add(new Employee(5, "Jorge", "Soporte Técnico", 1500.00, "IT"));
    }

    @GetMapping
    public List<Employee> getAll() { return employees; }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        return employees.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Employee create(@RequestBody Employee newEmployee) {
        employees.add(newEmployee);
        return newEmployee;
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable int id, @RequestBody Employee updated) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                updated.setId(id);
                employees.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Employee patch(@PathVariable int id, @RequestBody Employee partial) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                if (partial.getName() != null) e.setName(partial.getName());
                if (partial.getPosition() != null) e.setPosition(partial.getPosition());
                if (partial.getSalary() != 0.0) e.setSalary(partial.getSalary());
                if (partial.getDepartment() != null) e.setDepartment(partial.getDepartment());
                return e;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        employees.removeIf(e -> e.getId() == id);
        return "Empleado con ID " + id + " eliminado.";
    }
}
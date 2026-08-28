package com.lab.demo.controller;

import com.lab.demo.model.Vehicle;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehicleController {

    private List<Vehicle> vehicles = new ArrayList<>();

    public VehicleController() {
        vehicles.add(new Vehicle(1, "Toyota", "Corolla", 2020, 15000.00));
        vehicles.add(new Vehicle(2, "Honda", "Civic", 2022, 22000.00));
        vehicles.add(new Vehicle(3, "Suzuki", "Gixxer SF", 2023, 2500.00));
        vehicles.add(new Vehicle(4, "Ford", "Ranger", 2019, 18000.00));
        vehicles.add(new Vehicle(5, "Yamaha", "MT-03", 2021, 4500.00));
    }

    @GetMapping
    public List<Vehicle> getAll() { return vehicles; }

    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable int id) {
        return vehicles.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Vehicle create(@RequestBody Vehicle newVehicle) {
        vehicles.add(newVehicle);
        return newVehicle;
    }

    @PutMapping("/{id}")
    public Vehicle update(@PathVariable int id, @RequestBody Vehicle updated) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getId() == id) {
                updated.setId(id);
                vehicles.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Vehicle patch(@PathVariable int id, @RequestBody Vehicle partial) {
        for (Vehicle v : vehicles) {
            if (v.getId() == id) {
                if (partial.getBrand() != null) v.setBrand(partial.getBrand());
                if (partial.getModel() != null) v.setModel(partial.getModel());
                if (partial.getYear() != 0) v.setYear(partial.getYear());
                if (partial.getPrice() != 0.0) v.setPrice(partial.getPrice());
                return v;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        vehicles.removeIf(v -> v.getId() == id);
        return "Vehículo con ID " + id + " eliminado.";
    }
}
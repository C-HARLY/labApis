package com.lab.demo.controller;

import com.lab.demo.model.Order;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class OrderController {

    private List<Order> orders = new ArrayList<>();

    public OrderController() {
        orders.add(new Order(1, "Julio García", "Laptop", 1, 1200.00, "PENDIENTE"));
        orders.add(new Order(2, "Andrea Morales", "Monitor", 2, 600.00, "ENVIADO"));
        orders.add(new Order(3, "Marcos Paz", "Teclado", 1, 45.00, "ENTREGADO"));
        orders.add(new Order(4, "Lucía Fernández", "Mouse", 1, 25.50, "ENVIADO"));
        orders.add(new Order(5, "Esteban Reyes", "Escritorio", 1, 150.00, "PENDIENTE"));
    }

    @GetMapping
    public List<Order> getAll() { return orders; }

    @GetMapping("/{id}")
    public Order getById(@PathVariable int id) {
        return orders.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Order create(@RequestBody Order newOrder) {
        orders.add(newOrder);
        return newOrder;
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable int id, @RequestBody Order updated) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == id) {
                updated.setId(id);
                orders.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    // Ejemplo específico de PATCH para el estado, como lo pide la rúbrica
    @PatchMapping("/{id}")
    public Order patch(@PathVariable int id, @RequestBody Order partial) {
        for (Order o : orders) {
            if (o.getId() == id) {
                if (partial.getClient() != null) o.setClient(partial.getClient());
                if (partial.getProduct() != null) o.setProduct(partial.getProduct());
                if (partial.getQuantity() != 0) o.setQuantity(partial.getQuantity());
                if (partial.getTotal() != 0.0) o.setTotal(partial.getTotal());
                if (partial.getStatus() != null) o.setStatus(partial.getStatus());
                return o;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        orders.removeIf(o -> o.getId() == id);
        return "Pedido eliminado.";
    }
}
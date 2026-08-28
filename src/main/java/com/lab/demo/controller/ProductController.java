package com.lab.demo.controller;

import com.lab.demo.model.Product;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos") // Cumpliendo con el requerimiento de la tarea
public class ProductController {

    private List<Product> products = new ArrayList<>();

    // Constructor: Agregar al menos 5 productos iniciales
    public ProductController() {
        products.add(new Product(1, "Laptop", 1200.00, "Electronics"));
        products.add(new Product(2, "Mouse", 25.50, "Electronics"));
        products.add(new Product(3, "Keyboard", 45.00, "Electronics"));
        products.add(new Product(4, "Monitor", 300.00, "Electronics"));
        products.add(new Product(5, "Desk", 150.00, "Furniture"));
    }

    // 1. GET todos - /api/productos
    @GetMapping
    public List<Product> getAll() {
        return products;
    }

    // 2. GET por ID - /api/productos/{id}
    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null); 
    }

    // 3. POST - /api/productos
    @PostMapping
    public Product create(@RequestBody Product newProduct) {
        products.add(newProduct);
        return newProduct;
    }

    // 4. PUT - /api/productos/{id}
    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                updatedProduct.setId(id); // Asegurar que el ID no cambie por accidente
                products.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    // 5. PATCH - /api/productos/{id}
    @PatchMapping("/{id}")
    public Product patch(@PathVariable int id, @RequestBody Product partialProduct) {
        for (Product p : products) {
            if (p.getId() == id) {
                // Solo se actualizan los campos que no vienen vacíos o en cero
                if (partialProduct.getName() != null) p.setName(partialProduct.getName());
                if (partialProduct.getPrice() != 0.0) p.setPrice(partialProduct.getPrice());
                if (partialProduct.getCategory() != null) p.setCategory(partialProduct.getCategory());
                return p;
            }
        }
        return null;
    }

    // 6. DELETE - /api/productos/{id}
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        products.removeIf(p -> p.getId() == id);
        return "Producto con ID " + id + " eliminado correctamente.";
    }
}
package com.lab.demo.controller;

import com.lab.demo.model.Client;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    private List<Client> clients = new ArrayList<>();

    public ClientController() {
        clients.add(new Client(1, "Julio", "García", "julio@mail.com", "555-1010"));
        clients.add(new Client(2, "Andrea", "Morales", "andrea@mail.com", "555-2020"));
        clients.add(new Client(3, "Marcos", "Paz", "marcos@mail.com", "555-3030"));
        clients.add(new Client(4, "Lucía", "Fernández", "lucia@mail.com", "555-4040"));
        clients.add(new Client(5, "Esteban", "Reyes", "esteban@mail.com", "555-5050"));
    }

    @GetMapping
    public List<Client> getAll() { return clients; }

    @GetMapping("/{id}")
    public Client getById(@PathVariable int id) {
        return clients.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Client create(@RequestBody Client newClient) {
        clients.add(newClient);
        return newClient;
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable int id, @RequestBody Client updated) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getId() == id) {
                updated.setId(id);
                clients.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Client patch(@PathVariable int id, @RequestBody Client partial) {
        for (Client c : clients) {
            if (c.getId() == id) {
                if (partial.getName() != null) c.setName(partial.getName());
                if (partial.getLastName() != null) c.setLastName(partial.getLastName());
                if (partial.getEmail() != null) c.setEmail(partial.getEmail());
                if (partial.getPhone() != null) c.setPhone(partial.getPhone());
                return c;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        clients.removeIf(c -> c.getId() == id);
        return "Cliente eliminado.";
    }
}
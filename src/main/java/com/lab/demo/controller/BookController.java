package com.lab.demo.controller;

import com.lab.demo.model.Book;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class BookController {

    private List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1, "1984", "George Orwell", "Distopía", 15.99));
        books.add(new Book(2, "El Hobbit", "J.R.R. Tolkien", "Fantasía", 20.50));
        books.add(new Book(3, "Dune", "Frank Herbert", "Ciencia Ficción", 18.00));
        books.add(new Book(4, "Fahrenheit 451", "Ray Bradbury", "Ciencia Ficción", 14.50));
        books.add(new Book(5, "Drácula", "Bram Stoker", "Terror", 12.00));
    }

    @GetMapping
    public List<Book> getAll() { return books; }

    @GetMapping("/{id}")
    public Book getById(@PathVariable int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Book create(@RequestBody Book newBook) {
        books.add(newBook);
        return newBook;
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable int id, @RequestBody Book updated) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                updated.setId(id);
                books.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Book patch(@PathVariable int id, @RequestBody Book partial) {
        for (Book b : books) {
            if (b.getId() == id) {
                if (partial.getTitle() != null) b.setTitle(partial.getTitle());
                if (partial.getAuthor() != null) b.setAuthor(partial.getAuthor());
                if (partial.getGenre() != null) b.setGenre(partial.getGenre());
                if (partial.getPrice() != 0.0) b.setPrice(partial.getPrice());
                return b;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        books.removeIf(b -> b.getId() == id);
        return "Libro con ID " + id + " eliminado.";
    }
}
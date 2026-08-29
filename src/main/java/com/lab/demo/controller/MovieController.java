package com.lab.demo.controller;

import com.lab.demo.model.Movie;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class MovieController {

    private List<Movie> movies = new ArrayList<>();

    public MovieController() {
        movies.add(new Movie(1, "Inception", "Christopher Nolan", "Ciencia Ficción", 2010));
        movies.add(new Movie(2, "The Matrix", "Lana Wachowski, Lilly Wachowski", "Ciencia Ficción", 1999));
        movies.add(new Movie(3, "Interstellar", "Christopher Nolan", "Ciencia Ficción", 2014));
        movies.add(new Movie(4, "Parasite", "Bong Joon Ho", "Suspenso", 2019));
        movies.add(new Movie(5, "Spirited Away", "Hayao Miyazaki", "Animación", 2001));
    }

    @GetMapping
    public List<Movie> getAll() { return movies; }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable int id) {
        return movies.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Movie create(@RequestBody Movie newMovie) {
        movies.add(newMovie);
        return newMovie;
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable int id, @RequestBody Movie updated) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getId() == id) {
                updated.setId(id);
                movies.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Movie patch(@PathVariable int id, @RequestBody Movie partial) {
        for (Movie m : movies) {
            if (m.getId() == id) {
                if (partial.getTitle() != null) m.setTitle(partial.getTitle());
                if (partial.getDirector() != null) m.setDirector(partial.getDirector());
                if (partial.getGenre() != null) m.setGenre(partial.getGenre());
                if (partial.getYear() != 0) m.setYear(partial.getYear());
                return m;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        movies.removeIf(m -> m.getId() == id);
        return "Película con ID " + id + " eliminada.";
    }
}
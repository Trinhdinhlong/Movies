package com.mocktest.controllers;

import com.mocktest.entities.Movie;
import com.mocktest.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movie-management")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<?> getMovies(@RequestParam(defaultValue = "0") int pageNumber,
                                       @RequestParam(defaultValue = "5") int size) {
        Page<Movie> movies = movieService.getAll(pageNumber, size);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/movie")
    public ResponseEntity<?> addMovie(@Valid @RequestBody Movie movie) {
        Movie newMovie = movieService.create(movie);
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }

    @PostMapping("/movies")
    public ResponseEntity<?> addMovies(@Valid @RequestBody List<Movie> movies) {
        movieService.addAll(movies);
        return ResponseEntity.ok(movies);
    }
}

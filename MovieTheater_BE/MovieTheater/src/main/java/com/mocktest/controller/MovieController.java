package com.mocktest.controller;

import com.mocktest.dto.MovieDto;
import com.mocktest.entities.Movie;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie-management")
public class MovieController {
    private final MovieService movieService;
    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getAllMovie(){
        List<MovieDto> response = movieService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<MovieDto> deleteMovie(@RequestBody MovieDto request, @PathVariable Long id) throws NotFoundException {
        MovieDto response = movieService.deleteById(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/movie")
    public ResponseEntity<?> addMovie(@Valid @RequestBody Movie movie) {
        Movie newMovie = movieService.create(movie);
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }
}

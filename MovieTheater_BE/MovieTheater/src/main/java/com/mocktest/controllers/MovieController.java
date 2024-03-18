package com.mocktest.controllers;

import com.mocktest.bean.MovieResponse;
import com.mocktest.bean.MovieShowTimeResponse;
import com.mocktest.bean.MovieRequest;
import com.mocktest.entities.Movie;
import com.mocktest.entities.Type;
import com.mocktest.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovie(@RequestParam(required = false) String date){
        List<MovieShowTimeResponse> response;
        if (date == null) {
             response = movieService.getAll();
        } else {
            response = movieService.getAll(date);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/movies/admin")
    public ResponseEntity<List<MovieResponse>> getAllMovieByAdmin(){
        List<MovieResponse> movieResponses = movieService.getAllMovieByAdmin();
        return new ResponseEntity<>(movieResponses, HttpStatus.OK);
    }
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") Long request) {
        movieService.deleteById(request);
        return new ResponseEntity<>(String.valueOf(request), HttpStatus.OK);
    }
    @PostMapping("/movies")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto request) {
        Set<TypeMovie> typeMovies = new HashSet<>();
        for (Long typeId : request.getTypeMovieId()) {
            TypeMovie type = movieTypeService.getByTypeId(typeId);
            if (type != null) {
                typeMovies.add(type);
            }
        }
        request.setTypeMovies(typeMovies);
        MovieDto response = movieService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/movie")
    public ResponseEntity<?> createMovie(@RequestBody MovieRequest request) {
        MovieResponse newMovie = movieService.create(request);
        return new ResponseEntity<>(newMovie, HttpStatus.OK);
    }

    @PutMapping("/movie")
    public ResponseEntity<MovieRequest> updateMovie(@RequestBody MovieRequest request){
        MovieRequest response = movieService.updateById(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


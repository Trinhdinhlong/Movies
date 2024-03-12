package com.mocktest.controllers;

import com.mocktest.dto.MovieDto;
import com.mocktest.services.MovieService;
import com.mocktest.services.MovieTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie-management")
public class MovieController {
    private final MovieService movieService;
    private final MovieTypeService movieTypeService;
    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getAllMovie(){
        List<MovieDto> response = movieService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") Long request) {
        movieService.deleteById(request);
        return new ResponseEntity<>(String.valueOf(request), HttpStatus.OK);
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto request) {
        for (int i = 0; i < request.getTypeMovieId().size(); i++) {
            request.setTypeMovies(movieTypeService.getByTypeId(request.getTypeMovieId().get(i)));
        }
        MovieDto requests = movieService.create(request);
        return new ResponseEntity<>(requests, HttpStatus.CREATED);
    }
    @PutMapping("/movie")
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto request){
        MovieDto response = movieService.updateById(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


package com.mocktest.controllers;

import com.mocktest.bean.MovieShowTimeResponse;
import com.mocktest.dto.MovieDto;
import com.mocktest.entities.Type;
import com.mocktest.services.MovieService;
import com.mocktest.services.MovieTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie-management")
public class MovieController {
    private final MovieService movieService;
    private final MovieTypeService movieTypeService;

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
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") Long request) {
        movieService.deleteById(request);
        return new ResponseEntity<>(String.valueOf(request), HttpStatus.OK);
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto request) {
        List<Type> typeMovies = new ArrayList<>();
        for (Long typeId : request.getTypeMovieId()) {
            Type type = movieTypeService.getByTypeId(typeId);
            if (type != null) {
                typeMovies.add(type);
            }
        }
        request.setTypeMovies(typeMovies);
        MovieDto requests = movieService.create(request);
        System.out.println(requests);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PutMapping("/movie")
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto request){
        MovieDto response = movieService.updateById(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


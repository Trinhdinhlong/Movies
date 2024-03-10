package com.mocktest.controller;

import com.mocktest.dto.MovieDto;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class MovieController {
    private final MovieService movieService;
    @GetMapping("/listmovie")
    public ResponseEntity<List<MovieDto>> getAllMovie(){
        List<MovieDto> response = movieService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete/movie")
    public ResponseEntity<MovieDto> deleteMovie(@RequestBody MovieDto request) throws NotFoundException {
        MovieDto response = movieService.deleteById(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

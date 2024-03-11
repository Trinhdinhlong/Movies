package com.mocktest.services;

import com.mocktest.dto.MovieDto;
import com.mocktest.entities.Movie;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public List<MovieDto> getAll() {
        try {
            List<Movie> movieList = movieRepository.findAll();
            return movieList.stream()
                    .map(MovieDto::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving all roles", e);
        }
    }
    public MovieDto deleteById(MovieDto request) throws NotFoundException {
        if (movieRepository.existsById(request.getId())) {
            movieRepository.deleteById(request.getId());
            return request;
        }else {
            throw new NotFoundException("data not found in entity User: " + request.getId());
        }
    }
    public Movie create(Movie movie) {
//        movie.setCreatedDate(LocalDateTime.now());
        return movieRepository.save(movie);
    }
}

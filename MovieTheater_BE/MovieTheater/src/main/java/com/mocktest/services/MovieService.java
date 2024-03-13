package com.mocktest.services;

import com.mocktest.dto.MovieDto;
import com.mocktest.entities.Movie;
import com.mocktest.entities.Type;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.MovieRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public Page<Movie> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        if (!moviePage.hasContent()) {
            throw new NotFoundException("No data to display!");
        }
        return moviePage;
    }
    public List<MovieDto> getAll() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream()
                .map(MovieDto::new)
                .collect(Collectors.toList());
    }
    public void deleteById(Long request){
        if (movieRepository.existsById(request)) {
            movieRepository.deleteById(request);
        }else {
            throw new NotFoundException("data not found in entity User: " + request);
        }
    }
    public MovieDto create(MovieDto request){
        Movie requests = new Movie();
        BeanUtils.copyProperties(request, requests);
        Movie response = movieRepository.save(requests);
        return new MovieDto(response);
    }
    public MovieDto updateById(MovieDto request){
        Optional<Movie> userOptional = movieRepository.findById(request.getId());
        MovieDto response = new MovieDto();
        BeanUtils.copyProperties(userOptional, response);
        return response;
    }
}

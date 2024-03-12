package com.mocktest.services;

import com.mocktest.dto.MovieDto;
import com.mocktest.dto.UserDto;
import com.mocktest.entities.Movie;
import com.mocktest.entities.User;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.MovieRepository;
import com.mocktest.until.PasswordEncoderExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
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
        return new MovieDto(movieRepository.save(requests));
    }
    public MovieDto updateById(MovieDto request){
        Optional<Movie> userOptional = movieRepository.findById(request.getId());
        MovieDto response = new MovieDto();
        BeanUtils.copyProperties(userOptional, response);
        return response;
    }
}

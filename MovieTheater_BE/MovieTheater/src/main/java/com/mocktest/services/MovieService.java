package com.mocktest.services;

import com.mocktest.dto.MovieDto;
import com.mocktest.entities.Movie;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    public Movie create(Movie movie) {
        movie.setCreatedDate(LocalDateTime.now());
        return movieRepository.save(movie);
    }

    public List<Movie> addAll(List<Movie> movie) {
        return movieRepository.saveAll(movie);
    }

    public Movie updateById(Movie movie, Long id) {
        Movie movieFromDb = movieRepository.getById(id);
        if (movieFromDb != null) {
            if (movieRepository.existsMovieByDirectorAndMovieNameEnglishAndMovieNameEnglish(movie.getDirector(),
                    movie.getMovieNameVN(),
                    movie.getMovieNameEnglish()) != null) {
                throw new BadRequestException("Existed movie, please try again!");
            }
        }
        return null;
    }

    public List<MovieDto> getAll() {
            List<Movie> movieList = movieRepository.findAll();
            return movieList.stream()
                    .map(MovieDto::new)
                    .collect(Collectors.toList());
    }
    public MovieDto deleteById(MovieDto request) throws NotFoundException {
        if (movieRepository.existsById(request.getId())) {
            movieRepository.deleteById(request.getId());
            return request;
        }else {
            throw new NotFoundException("data not found in entity User: " + request.getId());
        }
    }
}

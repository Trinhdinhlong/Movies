package com.mocktest.services;

import com.mocktest.entities.Movie;
import com.mocktest.exceptions.DuplicateException;
import com.mocktest.exceptions.NoDataFoundException;
import com.mocktest.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
            throw new NoDataFoundException("No data to display!");
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
                throw new DuplicateException("Existed movie, please try again!");
            }
        }
        return null;
    }

    public Movie deleteById(Long id) {
        return null;
    }
}

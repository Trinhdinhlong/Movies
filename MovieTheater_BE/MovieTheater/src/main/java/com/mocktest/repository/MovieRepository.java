package com.mocktest.repository;

import com.mocktest.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie existsMovieByDirectorAndMovieNameEnglishAndMovieNameEnglish(String director,
                                                                        String movieNameVN,
                                                                        String movieNameEnglish);
}

package com.mocktest.repository;

import com.mocktest.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie existsMovieByDirectorAndMovieNameEnglishAndMovieNameEnglish(String director,
                                                                        String movieNameVN,
                                                                        String movieNameEnglish);
    List<Movie> findAllByStartedDateAndEndDate(LocalDateTime startDate, LocalDateTime endDate);
    @Query("FROM Movie m " +
            "WHERE m.endDate > :now")
    List<Movie> findAll(LocalDate now);
}

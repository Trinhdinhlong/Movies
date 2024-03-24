package com.mocktest.repository;

import com.mocktest.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("FROM Movie m " +
            "WHERE m.endDate >= :now AND m.Active = 'true'")
    List<Movie> findAll(LocalDate now);
    @Query("FROM Movie m " +
            "WHERE m.movieNameVN = :data OR m.movieNameEnglish = :data AND m.Active = 'true'")
    Movie SearchMoive(String data);
    @Query("FROM Movie m " +
            "INNER JOIN m.typeMovies mt " +
            "WHERE mt.typeName = :typeName AND m.Active = 'true'")
    List<Movie> getALlMovieByType(String typeName);
}

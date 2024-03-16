package com.mocktest.repository;

import com.mocktest.entities.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
    Boolean existsByIdAndMovieIdAndRoomId(Long showTimeId, Long movieId, Long roomId);
    @Query("SELECT MAX(m.duration)  \n" +
            "FROM ShowTime s \n" +
            "INNER JOIN s.movie m \n" +
            "WHERE m.id = :id\n")
    public Long getDurationMovieInShowTime(Long id);
}

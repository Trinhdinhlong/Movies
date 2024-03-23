package com.mocktest.repository;

import com.mocktest.entities.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalTime;
import java.util.List;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
    Boolean existsByIdAndMovieIdAndRoomId(Long showTimeId, Long movieId, Long roomId);
    @Query("SELECT m.duration \n" +
            "FROM ShowTime s \n" +
            "INNER JOIN Movie m ON m.id = s.movie.id\n" +
            "WHERE s.id = :id AND m.Active = '1'\n")
    Long getDurationMovieInShowTime(Long id);
    @Query("FROM ShowTime s " +
            "INNER JOIN Movie m ON m.id = s.movie.id " +
            "WHERE m.id = :id AND s.Active = '1'")
    List<ShowTime> getAllShowTimeById(Long id);
    @Query("SELECT s FROM ShowTime s WHERE s.startTime > :now AND s.Active = '1'")
    List<ShowTime> findAllAfterCurrentTime(@Param("now") LocalTime now);


}

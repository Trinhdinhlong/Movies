package com.mocktest.services;

import com.mocktest.bean.MovieRequest;
import com.mocktest.entities.Movie;
import com.mocktest.entities.Room;
import com.mocktest.entities.ShowTime;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.MovieRepository;
import com.mocktest.repository.RoomRepository;
import com.mocktest.repository.ShowTimeRepository;
import com.mocktest.until.ParseTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowTimeService {
    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MovieRepository movieRepository;

    public List<ShowTime> createShowTime(MovieRequest request, Movie movie) {
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new NotFoundException("Not found room with id " + request.getRoomId() + "!"));
        Movie movieFromDb = movieRepository.findById(movie.getId())
                .orElseThrow(() -> new NotFoundException("Not found movie with id " + request.getRoomId() + "!"));
        List<LocalTime> startTimes = request.getStartTime();
        return startTimes.stream().map(startTime -> {
             return showTimeRepository.save(
                    ShowTime.builder()
                            .room(room)
                            .movie(movieFromDb)
                            .startTime(startTime)
                            .endTime(startTime.plusMinutes(request.getDuration()))
                            .build()
            );
        }).collect(Collectors.toList());
    }
}

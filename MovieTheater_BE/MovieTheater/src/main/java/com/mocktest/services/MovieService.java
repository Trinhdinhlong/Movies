package com.mocktest.services;

import com.mocktest.bean.MovieShowTimeResponse;
import com.mocktest.dto.MovieDto;
import com.mocktest.entities.Movie;
import com.mocktest.entities.ShowTime;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.MovieRepository;
import com.mocktest.until.ParseTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Page<Movie> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        if (!moviePage.hasContent()) {
            throw new NotFoundException("No data to display!");
        }
        return moviePage;
    }
    public List<MovieShowTimeResponse> getAll() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream().map(data -> new MovieShowTimeResponse(data))
                .collect(Collectors.toList());
    }

    public List<MovieShowTimeResponse> getAll(String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        LocalTime time = ParseTime.convertToTime(dateTime);
        List<Movie> movieList = movieRepository.findAll();

        movieList  = movieList.stream().filter( movie -> ((movie.getStartedDate().isBefore(dateTime.toLocalDate())) && (movie.getEndDate().isAfter(dateTime.toLocalDate()))))
                .map(data -> {
                    List<ShowTime> showTimes = data.getShowTimes();
                    if(!CollectionUtils.isEmpty(showTimes)) {
                        data.setShowTimes(showTimes.stream().filter(showTime -> {
                            System.out.println("showtime: " + showTime);
                            LocalTime startTime = ParseTime.convertToTime(showTime.getStartTime());
                            return !startTime.isBefore(time);
                        }).collect(Collectors.toList()));
                    }
                    return data;
                }).collect(Collectors.toList());

        return movieList.stream().filter(movie -> movie.getShowTimes().size() > 0)
                .map(data -> new MovieShowTimeResponse(data))
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

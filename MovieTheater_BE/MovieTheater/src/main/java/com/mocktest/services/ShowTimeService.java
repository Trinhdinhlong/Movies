package com.mocktest.services;

import com.mocktest.entities.ShowTime;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowTimeService {
    @Autowired
    private ShowTimeRepository showTimeRepository;
    public ShowTime getById(Long id) {
        Optional<ShowTime> showTimeOptional = showTimeRepository.findById(id);
        ShowTime response = showTimeOptional.orElseThrow(() -> new NotFoundException("User not found with id"));
        return response;
    }
    public Long getDurationMovie(Long id){
        return showTimeRepository.getDurationMovieInShowTime(id);
    }
}

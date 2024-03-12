package com.mocktest.services;

import com.mocktest.entities.Type;
import com.mocktest.repository.MovieTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MovieTypeService {
    @Autowired
    private MovieTypeRepository movieTypeRepository;

    public Type getByTypeId(Long id){
        Type type = movieTypeRepository.getById(id);
        return type;
    }

}

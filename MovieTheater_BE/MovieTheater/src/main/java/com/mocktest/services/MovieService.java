package com.mocktest.services;

import com.mocktest.entities.Movie;

import java.util.List;

public interface MovieService extends BaseService<Movie> {
    List<Movie> addAll(List<Movie> movies);
}

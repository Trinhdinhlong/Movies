package com.mocktest.services;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<T> {
    Page<T> getAll(int page, int size);
    T create(T t);
    T updateById(T t, Long id);
    T deleteById(Long id);
}

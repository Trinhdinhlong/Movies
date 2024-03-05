package com.mocktest.services;

import java.util.List;

public interface BaseService<T> {
    List<T> getAll();
    T create(T t);
    T updateById(T t, Long id);
    boolean deleteById(Long id);
}

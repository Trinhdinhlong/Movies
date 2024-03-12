package com.mocktest.repository;

import com.mocktest.entities.Movie;
import com.mocktest.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieTypeRepository extends JpaRepository<Type, Long> {
}

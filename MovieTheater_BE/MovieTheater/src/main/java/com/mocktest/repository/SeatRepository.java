package com.mocktest.repository;

import com.mocktest.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    Seat findByIdAndSeatType(Long id, String type);
}

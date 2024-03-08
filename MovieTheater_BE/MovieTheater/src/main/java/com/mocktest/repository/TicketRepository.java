package com.mocktest.repository;

import com.mocktest.dto.BookingDto;
import com.mocktest.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT new com.mocktest.dto.BookingDto(t.id, u.userId, u.fullName, u.identituCard, u.phone, " +
            "m.movieNameVN, st.startTime, s, t.ticketType) " +
            "FROM User u " +
            "JOIN Ticket t ON u.userId = t.user.userId " +
            "JOIN ShowTime st ON t.showTime.id = st.id " +
            "JOIN Seat s ON t.seat.id = s.id " +
            "JOIN Movie m ON st.movie.id = m.id")
    List<BookingDto> getAllBookings();
}

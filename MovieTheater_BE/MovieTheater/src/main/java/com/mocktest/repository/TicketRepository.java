package com.mocktest.repository;

import com.mocktest.dto.BookingDto;
import com.mocktest.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT new com.mocktest.dto.BookingDto(t.id, u.userId, u.fullName, u.identityCard, u.phone, " +
            "m.movieNameVN, st.startTime, s, t.ticketType) " +
            "FROM User u, Ticket t, ShowTime st, Seat s, Movie m " +
            "WHERE u.userId = t.user.userId " +
            "AND t.showTime.id = st.id " +
            "AND t.seat.id = s.id " +
            "AND st.movie.id = m.id")
    List<BookingDto> getAllBookings();
}

package com.mocktest.repository;

import com.mocktest.dto.BookingDto;
import com.mocktest.dto.SeatDto;
import com.mocktest.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("FROM Ticket t WHERE t.user.userId =?1")
    List<Ticket> getTicketsByUserId(Long id);
    @Query("SELECT new com.mocktest.dto.BookingDto(t.id, u.userId, u.fullName, u.identityCard, u.phone, " +
            "m.movieNameVN, st.startTime, s, t.ticketType) " +
            "FROM User u, Ticket t, ShowTime st, Seat s, Movie m " +
            "WHERE u.userId = t.user.userId " +
            "AND t.showTime.id = st.id " +
            "AND t.seat.id = s.id " +
            "AND st.movie.id = m.id")
    List<BookingDto> getAllBookings();
    @Query("SELECT new com.mocktest.dto.SeatDto(s.seatColumn, s.seatRow, s.seatType, t.ticketType) " +
            "FROM Seat s JOIN s.tickets t " +
            "WHERE s.room.id = :roomId AND t.ticketType = 'BOOKED'")
    List<SeatDto> findBookedSeatsByRoomId(@Param("roomId") Long roomId);
    @Query("SELECT t.showTime.movie.movieName AS MovieName, " +
            "t.createdDate AS BookingDate, " +
            "SUM(CASE WHEN t.ticketType = :ticketType THEN 1 ELSE 0 END) AS Total, " +
            "t.ticketType AS TypeTicket " +
            "FROM Ticket t " +
            "WHERE t.showTime IS NOT NULL " +
            "GROUP BY t.showTime.movie.movieName, " +
            "t.createdDate, " +
            "t.ticketType")

}

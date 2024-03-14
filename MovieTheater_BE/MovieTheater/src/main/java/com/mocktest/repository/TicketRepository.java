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
    @Query("SELECT new com.mocktest.dto.SeatDto(s.seatColumn, s.seatRow, s.seatType, t.ticketType) " +
            "FROM Seat s JOIN s.tickets t " +
            "WHERE s.room.id = :roomId AND t.ticketType = 'BOOKED'")
    List<SeatDto> findBookedSeatsByRoomId(@Param("roomId") Long roomId);
    @Query("FROM Ticket t " +
            "JOIN ShowTime s ON s.id = t.showTime.id " +
            "WHERE s.id = :showTimeId AND t.endTime > :now")
    List<Ticket> getAllSeatBookedInTicket(Long showTimeId, LocalDateTime now);

}

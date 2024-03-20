package com.mocktest.repository;
import com.mocktest.bean.BookedAndCancelTicketResponse;
import com.mocktest.bean.BookingListResponse;
import com.mocktest.entities.Ticket;
import com.mocktest.entities.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("FROM Ticket t " +
            "JOIN ShowTime s ON s.id = t.showTime.id " +
            "WHERE s.id = :showTimeId AND t.endTime > :now")
    List<Ticket> getAllSeatBookedInTicket(Long showTimeId, LocalTime now);
    @Transactional
    @Modifying
    @Query("UPDATE Ticket t " +
            "SET t.ticketType = :ticketType " +
            "WHERE t.id = :id")
    void UpdateStatusTicket(@Param("id") Long id, @Param("ticketType") TicketStatus ticketType);
    @Query("SELECT new com.mocktest.bean.BookedAndCancelTicketResponse(m.movieNameVN, SUM(s2.price), t.createdDate ,t.ticketType) " +
            "FROM Ticket t " +
            "INNER JOIN t.showTime s " +
            "INNER JOIN s.movie m " +
            "INNER JOIN t.seat s2 " +
            "GROUP BY m.movieNameVN, t.createdDate, t.ticketType")
    List<BookedAndCancelTicketResponse> getTotalPriceByTicket();

    @Query("SELECT new com.mocktest.bean.BookingListResponse(t.id, t.user.userId, u.fullName, u.identityCard, u.phone, m.movieNameVN, t.startTime, s2.seatColumn, s2.seatRow, t.ticketType) " +
            "FROM Ticket t " +
            "INNER JOIN t.user u " +
            "INNER JOIN t.showTime s " +
            "INNER JOIN s.movie m " +
            "INNER JOIN t.seat s2")
    List<BookingListResponse> getAllBookingUser();

}

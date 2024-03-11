package com.mocktest.repository;

import com.mocktest.entities.Ticket;
import com.mocktest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("FROM Ticket t WHERE t.user.id =?1")
    List<Ticket> getTicketsByUserId(Long id);
}

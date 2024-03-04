package com.mocktest.entities;

import javax.persistence.*;

@Entity
@Table(name = "tickets", schema = "dbo")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;
    @Column(name = "showtimeId")
    private int showTimeId;
    @Column(name = "seatId")
    private int seatId;
    @Column(name = "price")
    private double price;
    @Column(name = "ticketType")
    private int ticketType;

}

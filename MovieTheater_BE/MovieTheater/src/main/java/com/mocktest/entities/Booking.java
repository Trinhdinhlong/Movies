package com.mocktest.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking", schema = "dbo")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;
    @Column(name = "userId")
    private int userId;
    @Column(name = "ticketId")
    private int ticketId;
    @Column(name = "totalAmount")
    private double totalAmount;
    @Column(name = "bookingTime")
    private LocalDateTime bookingTime;
}

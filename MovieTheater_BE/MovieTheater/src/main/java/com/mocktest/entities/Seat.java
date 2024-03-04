package com.mocktest.entities;

import javax.persistence.*;

@Entity
@Table(name = "seats", schema = "dbo")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;
    @Column(name = "roomId")
    private int roomId;
    @Column(name = "seatColumn", length = 2)
    private String seatColumn;
    @Column(name = "seatRow")
    private int seatRow;
    @Column(name = "seatStatus")
    private int seatStatus;
    @Column(name = "seatType")
    private int seatType;

}

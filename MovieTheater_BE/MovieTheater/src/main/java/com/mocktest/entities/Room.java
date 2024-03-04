package com.mocktest.entities;

import javax.persistence.*;

@Entity
@Table(name = "rooms", schema = "dbo")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;
    @Column(name = "roomName")
    private String roomName;
    @Column(name = "seatQuantity")
    private int seatQuantity;
}

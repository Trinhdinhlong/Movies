package com.mocktest.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "showTimes", schema = "dbo")
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id")
    private Long id;
    @Column(name = "movieId")
    private int movieId;
    @Column(name = "roomId")
    private int roomId;
    @Column(name = "startTime")
    private LocalDateTime startTime;
    @Column(name = "endTime")
    private LocalDateTime endTime;
}

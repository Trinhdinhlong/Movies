package com.mocktest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tickets", schema = "dbo")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @Column(name = "ticket_type")
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "seat_id", referencedColumnName = "seat_id")
    private Seat seat;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "showtime_id", referencedColumnName = "showtime_id")
    private ShowTime showTime;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}

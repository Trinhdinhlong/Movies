package com.mocktest.dto;

import com.mocktest.entities.Seat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {
    private Long userId;
    private String fullName;
    private String identityCard;
    private String phone;
    private String email;
    private String movieName;
    private String imageUrl;
    private String roomName;
    private LocalDateTime date;
    private LocalDateTime startTime;
    private List<Seat> seats;
    private List<Double> price;
    private Double total;
}
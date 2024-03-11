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
    private String roomName;
    private LocalDateTime date;
    private LocalDateTime time;
    private List<Seat> seats;
}
package com.mocktest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.Seat;
import com.mocktest.entities.TicketType;
import com.mocktest.entities.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {
    private Long id;
    private Long userId;
    private String fullName;
    private String identityCard;
    private String phone;
    private String movieNameVN;
    private LocalDateTime startTime;
    private Seat seat;
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;
}

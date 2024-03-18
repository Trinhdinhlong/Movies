package com.mocktest.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.Seat;
import com.mocktest.entities.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookedAndCancelTicketResponse {
    private String movieNameVN;
    private double totalAmount;
    private LocalTime startTime;
    private Seat seat;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketType;
}

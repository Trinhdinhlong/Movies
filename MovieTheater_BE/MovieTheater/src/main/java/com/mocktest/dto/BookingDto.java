package com.mocktest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {
    private Long id;
    private Long userId;
    private String fullName;
    private String identituCard;
    private String phone;
    private String movieNameVN;
    private LocalDateTime startTime;
    private Seat seat;

}

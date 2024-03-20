package com.mocktest.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomRequest {
    private String nameRoom;
    private int seatQuantity ;
}

package com.mocktest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.Role;
import com.mocktest.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto {
    private Long id;
    private String roomName;
    private int seatQuantity;
    public RoomDto(Room entity) {
        if (entity != null) {
            BeanUtils.copyProperties(entity, this);
        }
    }
}

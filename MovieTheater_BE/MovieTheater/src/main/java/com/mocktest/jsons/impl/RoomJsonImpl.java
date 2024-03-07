package com.mocktest.jsons.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mocktest.dto.RoomDto;
import com.mocktest.jsons.RoomJson;
import com.mocktest.payload.ResponseData;
import com.mocktest.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomJsonImpl implements RoomJson {
    private final RoomService roomService;
    private final ObjectMapper objectMapper;

    @Override
    public String ToJson() throws JsonProcessingException {
        List<RoomDto> roomDto = roomService.getAll();
        ResponseData responseData = ResponseData.builder()
                .isSuccess(true)
                .data(roomDto)
                .build();
        return objectMapper.writeValueAsString(responseData);
    }
}

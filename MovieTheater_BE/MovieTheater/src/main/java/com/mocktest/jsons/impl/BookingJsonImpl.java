package com.mocktest.jsons.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mocktest.dto.BookingDto;
import com.mocktest.jsons.BookingJson;
import com.mocktest.payload.ResponseData;
import com.mocktest.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BookingJsonImpl implements BookingJson {
    private final BookingService bookingService;
    @Override
    public ResponseData toJson(){
        List<BookingDto> bookingDtoList = bookingService.getAllBookings();
        return ResponseData.builder()
                .isSuccess(true)
                .data(bookingDtoList)
                .build();
    }
}

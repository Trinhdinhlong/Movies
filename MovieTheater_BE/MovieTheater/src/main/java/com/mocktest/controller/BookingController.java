package com.mocktest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mocktest.jsons.BookingJson;
import com.mocktest.payload.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/booking")
public class BookingController{
    private final BookingJson bookingJson;
    @GetMapping
    public ResponseData getAllBookingTicket() throws JsonProcessingException {
        return bookingJson.toJson();
    }
}

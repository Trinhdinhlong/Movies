package com.mocktest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mocktest.dto.BookingDto;
import com.mocktest.entities.Ticket;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.services.BookingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class BookingController{
    private final BookingServiceImpl bookingService;
    @GetMapping("/booking")
    public ResponseEntity<List<BookingDto>> getAllBookingTicket() throws NotFoundException {
        List<BookingDto> bookingDto = bookingService.getAllBookings();
        return new  ResponseEntity<>(bookingDto, HttpStatus.OK);
    }
}

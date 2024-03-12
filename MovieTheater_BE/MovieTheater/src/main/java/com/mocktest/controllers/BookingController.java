package com.mocktest.controllers;

import com.mocktest.dto.BookingDto;
import com.mocktest.dto.SeatDto;
import com.mocktest.entities.Ticket;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/ticket-management")
public class BookingController{
    private final TicketService ticketService;
//    @GetMapping("/booking")
//    public ResponseEntity<List<BookingDto>> getAllBookingTicket(){
//        List<BookingDto> bookingDto = ticketService.getAllBookings();
//        return new  ResponseEntity<>(bookingDto, HttpStatus.OK);
//    }
//    @PostMapping("/seat/{roomId}")
//    public ResponseEntity<List<SeatDto>> getAllSeatBooked(@PathVariable("roomId") Long roomId){
//        List<SeatDto> response = ticketService.getAllSeatBookedInRoom(roomId);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}

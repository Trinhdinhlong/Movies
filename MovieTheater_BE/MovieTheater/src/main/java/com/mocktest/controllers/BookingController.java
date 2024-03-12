package com.mocktest.controllers;

import com.mocktest.services.TicketService;
import lombok.RequiredArgsConstructor;
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

package com.mocktest.controllers;

import com.mocktest.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/ticket-management")
public class BookingController{
//    @Autowired
//    private TicketService ticketService;
//    @GetMapping("/booking")
//    public ResponseEntity<List<BookingDto>> getAllBookingTicket(){
//        List<BookingDto> bookingDto = ticketService.getAllBookings();
//        return new  ResponseEntity<>(bookingDto, HttpStatus.OK);
//    }
//    @PostMapping("/seat/{roomId}")
//    public ResponseEntity<List<SeatDto>> getAllSeatBooked(@PathVariable("roomId") Long roomId){
//        List<SeatDto> response = ticketService.getAllSeatBookedInRoom(roomId);

//    private final TicketService bookingService;

//    @GetMapping("/booking")
//    public ResponseEntity<List<BookingDto>> getAllBookingTicket() throws NotFoundException {
////        List<BookingDto> bookingDto = bookingService.getAllBookings();
//        return new  ResponseEntity<>(bookingDto, HttpStatus.OK);
//    }
//    @PostMapping("/seat/{roomId}")
//    public ResponseEntity<List<SeatDto>> getAllSeatBooked(@PathVariable("roomId") Long roomId) throws NotFoundException {
//        List<SeatDto> response = bookingService.getAllSeatBookedInRoom(roomId);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}

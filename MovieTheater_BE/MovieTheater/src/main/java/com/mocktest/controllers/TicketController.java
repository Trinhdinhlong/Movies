package com.mocktest.controllers;
import com.mocktest.bean.*;
import com.mocktest.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TicketController {
    private final TicketService ticketService;
    @PostMapping("ticket/booking")
    public ResponseEntity<List<BookingTicketResponse>> saveAllBooking
            (@RequestBody List<BookingTicketRequest> bookingTicketRequest){
        return new ResponseEntity<>(ticketService.saveTicket(bookingTicketRequest), HttpStatus.OK);
    }
    @PutMapping("/ticket/{id}")
    public ResponseEntity<TicketStatusResponse> UpdateStatusTicket(@PathVariable("id") Long id){
        ticketService.UpdateStatusTicket(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/ticket/admin/{id}")
    public ResponseEntity<CofirmTicketResponse> getCofirmByAdmin(@PathVariable("id") Long id){
        return new ResponseEntity<>(ticketService.getCofirmAdminByTicketId(id), HttpStatus.OK);
    }
    @GetMapping("ticket/booked")
    public ResponseEntity<List<BookedAndCancelTicketResponse>> getAllTicketHasBookedANDGotten(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Access-Control-Allow-Origin", "*");
        return ResponseEntity.ok().headers(headers).body(ticketService.getAllBookedList());
    }
    @GetMapping("/ticket/cancel")
    public ResponseEntity<List<BookedAndCancelTicketResponse>> getAllTicketHasAbort(){
        return new ResponseEntity<>(ticketService.getAllCancelList(), HttpStatus.OK);
    }
    @GetMapping("/ticket/history")
    public ResponseEntity<List<HistoryTicketResponse>> getAllTicketHistory(){
        return new ResponseEntity<>(ticketService.getAllHistoryList(), HttpStatus.OK);
    }
    @GetMapping("/ticket/booking")
    public ResponseEntity<List<BookingListResponse>> getAllBookingByAdmin(){
        return new ResponseEntity<>(ticketService.getAllBookingUser(), HttpStatus.OK);

    }
}

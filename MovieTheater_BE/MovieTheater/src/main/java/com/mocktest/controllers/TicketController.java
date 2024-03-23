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
@CrossOrigin("*")
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
        System.out.println(id);
        System.out.println(ticketService.getCofirmAdminByTicketId(id));
        return new ResponseEntity<>(ticketService.getCofirmAdminByTicketId(id), HttpStatus.OK);
    }
    @GetMapping("ticket/booked/{userId}")
    public ResponseEntity<List<BookedAndCancelTicketResponse>> getAllTicketHasBookedANDGotten
            (@PathVariable("userId") Long userId){
        return new ResponseEntity<> (ticketService.getAllBookedList(userId), HttpStatus.OK);
    }
    @GetMapping("/ticket/cancel/{userId}")
    public ResponseEntity<List<BookedAndCancelTicketResponse>> getAllTicketHasAbort
            (@PathVariable("userId") Long userId){
        return new ResponseEntity<>(ticketService.getAllCancelList(userId), HttpStatus.OK);
    }
    @GetMapping("/ticket/history/{userId}")
    public ResponseEntity<List<HistoryTicketResponse>> getAllTicketHistory
            (@PathVariable("userId") Long userId){
        return new ResponseEntity<>(ticketService.getAllHistoryList(userId), HttpStatus.OK);
    }
    @GetMapping("/ticket/booking")
    public ResponseEntity<List<BookingListResponse>> getAllBookingByAdmin(){
        return new ResponseEntity<>(ticketService.getAllBookingUser(), HttpStatus.OK);

    }
}

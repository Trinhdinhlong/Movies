package com.mocktest.controllers;

import com.mocktest.dto.TicketDto;
import com.mocktest.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket-management")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/ticket")
    public ResponseEntity<?> getTicket(@RequestBody TicketDto ticketDto) {
        Long userId = ticketDto.getUserId();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/ticket")
    public ResponseEntity<?> createTicket(@RequestBody TicketDto ticketDto) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

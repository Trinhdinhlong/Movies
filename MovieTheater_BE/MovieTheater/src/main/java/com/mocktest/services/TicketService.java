package com.mocktest.services;

import com.mocktest.dto.BookingDto;
import com.mocktest.dto.SeatDto;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@EnableScheduling
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    public List<BookingDto> getAllBookings() throws NotFoundException {
        List<BookingDto> bookingDtoList = ticketRepository.getAllBookings();
        if(bookingDtoList != null){
            return bookingDtoList;
        }
        throw new NotFoundException("Not Found data enitites");
    }
    public List<SeatDto> getAllSeatBookedInRoom(Long request) throws NotFoundException {
        List<SeatDto> response = ticketRepository.findBookedSeatsByRoomId(request);
        if(response == null){
            throw new NotFoundException("Not Found data enitites");
        }
        return response;
    }
//    @Scheduled(fixedRate = 60000)
//    public void updateTicketStatus() {
//        ticketRepository.updateTicketStatus(LocalDateTime.now());
//    }
}

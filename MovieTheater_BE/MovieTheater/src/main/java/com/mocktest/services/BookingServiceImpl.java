package com.mocktest.services;

import com.mocktest.dto.BookingDto;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingServiceImpl {
    @Autowired
    private TicketRepository ticketRepository;
    public List<BookingDto> getAllBookings() throws NotFoundException {
        List<BookingDto> bookingDtoList = ticketRepository.getAllBookings();
        if(bookingDtoList != null){
            return bookingDtoList;
        }
        throw new NotFoundException("Not Found data enitites");
    }
}

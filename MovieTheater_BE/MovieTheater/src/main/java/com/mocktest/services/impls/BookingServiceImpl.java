package com.mocktest.services.impls;

import com.mocktest.dto.BookingDto;
import com.mocktest.repository.TicketRepository;
import com.mocktest.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public List<BookingDto> getAll() {
        return null;
    }

    @Override
    public BookingDto getById(Long id) {
        return null;
    }

    @Override
    public BookingDto create(BookingDto bookingDto) {
        return null;
    }

    @Override
    public BookingDto updateById(BookingDto bookingDto, Long id) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public List<BookingDto> getAllBookings() {
        List<BookingDto> bookingDtoList = ticketRepository.getAllBookings();
        if(bookingDtoList != null){
            return bookingDtoList;
        }
        throw new StackOverflowError("Error");
    }
}

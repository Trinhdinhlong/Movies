package com.mocktest.services;

import com.mocktest.dto.BookingDto;

import java.util.List;

public interface BookingService extends BaseService<BookingDto> {
      List<BookingDto> getAllBookings();
}

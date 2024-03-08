package com.mocktest.jsons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mocktest.dto.BookingDto;
import com.mocktest.payload.ResponseData;

public interface BookingJson {
    ResponseData toJson() throws JsonProcessingException;
}

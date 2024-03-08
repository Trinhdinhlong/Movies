package com.mocktest.utils;

import com.mocktest.dto.BookingDto;
import com.mocktest.dto.ResponseData;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BeanUtil {

    public ResponseData buildResponseSuccessCase(Object data){
        return ResponseData.builder()
                .isSuccess(true)
                .data(data)
                .build();
    }
}

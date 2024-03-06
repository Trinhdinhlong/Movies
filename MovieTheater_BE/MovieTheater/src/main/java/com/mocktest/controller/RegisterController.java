package com.mocktest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mocktest.dto.UserDto;
import com.mocktest.Json.UserJson;
import com.mocktest.payload.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/register")
public class RegisterController {
    private UserJson userLoad;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData saveUser(@RequestBody String json) throws JsonProcessingException {
        UserDto savedUserDto = userLoad.CreateUserDto(json);
        return ResponseData.builder()
                .isSuccess(true)
                .data(savedUserDto)
                .build();
    }
}

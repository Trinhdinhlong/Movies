package com.mocktest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mocktest.dto.UserDto;
import com.mocktest.jsons.UserJson;
import com.mocktest.payload.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/register")
public class RegisterController {
    private final UserJson userJson;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData saveUser(@RequestBody UserDto userDto) throws JsonProcessingException {
        return userJson.CreateUser(userDto);
    }
}

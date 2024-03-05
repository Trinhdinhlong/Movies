package com.mocktest.controller;

import com.mocktest.dto.UserDto;
import com.mocktest.entities.User;
import com.mocktest.payload.ResponseData;
import com.mocktest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/teams")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public ResponseData save(@RequestBody UserDto userDto){
        return ResponseData.builder()
                .isSuccess(true)
                .data(userService.create(userDto))
                .build();
    }
}

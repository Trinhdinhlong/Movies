package com.mocktest.controller;
import com.mocktest.dto.UserDto;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> CheckLogin(@RequestBody UserDto userDto) throws BadRequestException, NotFoundException, AuthenticationException {
        UserDto userDtoSaved = userService.login(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) throws BadRequestException, NotFoundException {
        UserDto response = userService.create(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/profile")
    public ResponseEntity<UserDto> updateUserEntity(@RequestBody UserDto userDto) throws BadRequestException, NotFoundException {
        userService.updateByUserName(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}

package com.mocktest.controller;
import com.mocktest.dto.UserDto;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mocktest.exceptions.AuthenticationException;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> Login(@RequestBody UserDto userDto) throws BadRequestException,NotFoundException, AuthenticationException{
        UserDto userDtoSaved = userService.login(userDto);
        return new ResponseEntity<>(userDtoSaved, HttpStatus.OK);
    }
    @GetMapping("/user")
    private ResponseEntity<List<UserDto>> getall(){
        List<UserDto> userDtoList = userService.getAll();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) throws BadRequestException, NotFoundException {
        UserDto response = userService.create(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/profile")
    public ResponseEntity<UserDto> updateUserEntity(@RequestBody UserDto request) throws BadRequestException, NotFoundException {
        userService.updateById(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}

package com.mocktest.controller;
import com.mocktest.dto.RoleDto;
import com.mocktest.dto.UserDto;
import com.mocktest.entities.Role;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.MethodArgumentNotValidException;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.services.RoleService;
import com.mocktest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
    private final RoleService roleService;
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> LoginUser(@RequestBody UserDto userDto) throws BadRequestException,NotFoundException, AuthenticationException{
        UserDto userDtoSaved = userService.login(userDto);
        return new ResponseEntity<>(userDtoSaved, HttpStatus.OK);
    }
    @PostMapping("/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) throws BadRequestException, MethodArgumentNotValidException {
        RoleDto roleDto = roleService.getById(2L);
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        userDto.setRole(role);
        UserDto response = userService.create(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/employee/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> saveEmployee(@RequestBody UserDto userDto) throws BadRequestException, MethodArgumentNotValidException {
        RoleDto roleDto = roleService.getById(3L);
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        userDto.setRole(role);
        UserDto response = userService.create(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/profile")
    public ResponseEntity<UserDto> updateUserEntity(@RequestBody UserDto request) throws BadRequestException, NotFoundException {
        userService.updateById(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}

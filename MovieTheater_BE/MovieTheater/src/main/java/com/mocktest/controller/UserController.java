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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.mocktest.exceptions.AuthenticationException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-management")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> LoginUser(@RequestBody UserDto userDto) throws BadRequestException,NotFoundException, AuthenticationException{
        UserDto userDtoSaved = userService.login(userDto);
        return new ResponseEntity<>(userDtoSaved, HttpStatus.OK);
    }
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto request) throws BadRequestException, MethodArgumentNotValidException {
        RoleDto roleDto = roleService.getById(2L);
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        request.setRole(role);
        UserDto response = userService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> saveEmployee(@RequestBody @Valid UserDto request) throws BadRequestException, MethodArgumentNotValidException {
        RoleDto roleDto = roleService.getById(3L);
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        request.setRole(role);
        UserDto response = userService.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/profile")
    public ResponseEntity<UserDto> updateUserEntity(@RequestBody @Valid UserDto request) throws BadRequestException, NotFoundException {
        userService.updateById(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}

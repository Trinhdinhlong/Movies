package com.mocktest.controllers;
import com.mocktest.dto.UserDto;
import com.mocktest.entities.Role;
import com.mocktest.services.RoleService;
import com.mocktest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    @GetMapping("/user")
    public ResponseEntity<UserDto> LoginUser(@RequestBody UserDto userDto){
        UserDto userDtoSaved = userService.login(userDto);
        return new ResponseEntity<>(userDtoSaved, HttpStatus.OK);
    }
    @PostMapping("/user")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto request){
        Role role = roleService.getById(2L);
        request.setRole(role);
        UserDto response = userService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/employee")
    public ResponseEntity<UserDto> saveEmployee(@RequestBody UserDto request){
        Role role= roleService.getById(3L);
        request.setRole(role);
        UserDto response = userService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/employee")
    public ResponseEntity<List<UserDto>> listEmployee(){
        List<UserDto> response = userService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/profile")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto request){
        userService.updateById(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long request){
        userService.deleteById(request);
        return new ResponseEntity<>(String.valueOf(request), HttpStatus.OK);
    }
}

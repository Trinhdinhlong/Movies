package com.mocktest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mocktest.dto.UserDto;
import com.mocktest.Json.UserJson;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/login")
public class LoginController {
    private UserJson userLoad;
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getAll(@PathVariable("id") Long id, String json) throws JsonProcessingException {
        UserDto userDto = userLoad.CreateUserDto(json);
        return ResponseEntity.ok(userLoad.ToJson(id, userDto));
    }
}

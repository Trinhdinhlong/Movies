package com.mocktest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mocktest.dto.UserDto;
import com.mocktest.jsons.UserJson;

import com.mocktest.payload.ResponseData;
import com.mocktest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {
    private final UserJson userLoad;
    @GetMapping("/{username}/{password}")
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<String> getById(@PathVariable("username") String username,@PathVariable("password") String password)
            throws JsonProcessingException {
         String message = userLoad.ToJson(username, password);
         return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

package com.mocktest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mocktest.jsons.UserJson;
import com.mocktest.payload.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private final UserJson userJson;
    @PutMapping
    public  ResponseData updateUserEntity(@RequestBody String json) throws JsonProcessingException {
        return userJson.UpdateUserAndAdmin(json);
    }
}

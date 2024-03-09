package com.mocktest.controller;
import com.mocktest.dto.RoomDto;
import com.mocktest.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController("/api/room")
public class CinemaController {
    @Autowired
    private RoomService roomService;
    @GetMapping
    public ResponseEntity<List<RoomDto>> getCinema(){
        List<RoomDto> roomDtos = roomService.getAll();
        return new ResponseEntity<>(roomDtos, HttpStatus.OK);
    }
}

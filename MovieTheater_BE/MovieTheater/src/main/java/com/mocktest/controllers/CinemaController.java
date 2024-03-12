package com.mocktest.controllers;
import com.mocktest.dto.RoomDto;
import com.mocktest.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room-management")
public class CinemaController {
    private final RoomService roomService;
    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDto>> getCinema(){
        List<RoomDto> roomDtos = roomService.getAll();
        return new ResponseEntity<>(roomDtos, HttpStatus.OK);
    }
}

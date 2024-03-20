package com.mocktest.controllers;

import com.mocktest.bean.RoomRequest;
import com.mocktest.bean.RoomResponse;
import com.mocktest.entities.Room;
import com.mocktest.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomController {
    private final RoomService roomService;
    @PostMapping("/room")
    public ResponseEntity<RoomResponse> createRoom(@RequestBody RoomRequest request){
        return new ResponseEntity<>(roomService.create(request), HttpStatus.OK);
    }
    @GetMapping("/room")
    public ResponseEntity<List<RoomResponse>> getAllRoom(){
        return new ResponseEntity<>(roomService.getAll(), HttpStatus.OK);
    }
}

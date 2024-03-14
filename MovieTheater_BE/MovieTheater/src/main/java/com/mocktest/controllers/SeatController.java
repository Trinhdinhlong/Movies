package com.mocktest.controllers;

import com.mocktest.bean.SeatDetailResponse;
import com.mocktest.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SeatController {
    private final SeatService seatService;
    @GetMapping("/movie/{movieId}/room/{roomId}/showtime/{showTimeId}/seats")
    public ResponseEntity<Object> getAllSeatByMoive(@PathVariable("movieId") Long movieId
            , @PathVariable("roomId") Long roomId, @PathVariable("showTimeId") Long showTimeId){
        return new ResponseEntity<>(seatService.getAllSeatByMovieAndRoom(roomId,movieId, showTimeId), HttpStatus.OK);
    }
}

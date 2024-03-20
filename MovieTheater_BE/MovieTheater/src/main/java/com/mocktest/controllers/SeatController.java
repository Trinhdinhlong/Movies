package com.mocktest.controllers;

import com.mocktest.bean.SeatTypeResponse;
import com.mocktest.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/seats")
    public ResponseEntity<List<SeatTypeResponse>> updateStatusSeat(@RequestBody List<Long> request){
        return new ResponseEntity<>( seatService.updateTypeSeatById(request), HttpStatus.OK);
    }
}

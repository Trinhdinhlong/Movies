package com.mocktest.services;

import com.mocktest.bean.RoomRequest;
import com.mocktest.bean.RoomResponse;
import com.mocktest.entities.Room;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    public List<RoomResponse> getAll() {
            List<Room> rooms = roomRepository.findAll();
            if(rooms.isEmpty()) {
                throw new NotFoundException("Error while retrieving all roles");

            }
            List<RoomResponse> responses = new ArrayList<>();
            for(Room room : rooms){
                RoomResponse response = new RoomResponse();
                response.setId(room.getId());
                response.setNameRoom(room.getRoomName());
                response.setSeatQuantity(room.getSeatQuantity());
                responses.add(response);
            }
            return responses;
    }
    public RoomResponse getById(Long id) {
            Optional<Room> roomOptional = roomRepository.findById(id);
            Room room = roomOptional.orElseThrow(() -> new NotFoundException("Room not found with id"));
            RoomResponse response = new RoomResponse();
            response.setId(room.getId());
            response.setNameRoom(room.getRoomName());
            response.setSeatQuantity(response.getSeatQuantity());
            return response;
    }
    public RoomResponse create(RoomRequest request){
            Room room = new Room();
            room.setRoomName(request.getNameRoom());
            room.setSeatQuantity(request.getSeatQuantity());
            room = roomRepository.save(room);
            RoomResponse response = new RoomResponse();
            response.setId(room.getId());
            response.setNameRoom(room.getRoomName());
            response.setSeatQuantity(request.getSeatQuantity());
            return response;
    }
    public  RoomResponse updateById(RoomResponse response){
        Optional<Room> roomOptional = roomRepository.findById(response.getId());
        Room room = roomOptional.orElseThrow(() -> new NotFoundException("Room not found with id"));;
        RoomResponse responses = new RoomResponse();
        responses.setId(room.getId());
        responses.setNameRoom(room.getRoomName());
        responses.setSeatQuantity(room.getSeatQuantity());
        return responses;
    }

    public void deleteById(Long id) {
            if (roomRepository.existsById(id)) {
                roomRepository.deleteById(id);
            } else {
                throw new NotFoundException("User not found with id: " + id);
            }
    }
}

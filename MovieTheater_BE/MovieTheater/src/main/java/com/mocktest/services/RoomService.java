package com.mocktest.services;

import com.mocktest.dto.RoomDto;
import com.mocktest.entities.Room;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    public List<RoomDto> getAll() {
            List<Room> rooms = roomRepository.findAll();
            if(rooms.isEmpty()) {
                throw new NotFoundException("Error while retrieving all roles");

            }
            return rooms.stream()
                .map(RoomDto::new)
                .collect(Collectors.toList());
    }
    public RoomDto getById(Long id) {
            Optional<Room> roleOptional = roomRepository.findById(id);
            Room room = new Room();
            BeanUtils.copyProperties(roleOptional, room);
            return new RoomDto(roomRepository.save(room));
    }
    public RoomDto create(RoomDto roomDto){
            Room room = new Room();
            BeanUtils.copyProperties(roomDto, room);
            return new RoomDto(roomRepository.save(room));
    }
    public RoomDto updateById(RoomDto request){
            Optional<Room> roomOptional = roomRepository.findById(request.getId());
            Room room = new Room();
            BeanUtils.copyProperties(roomOptional,room );
            return new RoomDto(roomRepository.save(room));
    }

    public void deleteById(Long id) {
            if (roomRepository.existsById(id)) {
                roomRepository.deleteById(id);
            } else {
                throw new NotFoundException("User not found with id: " + id);
            }
    }
}

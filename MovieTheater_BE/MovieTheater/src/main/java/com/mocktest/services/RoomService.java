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
            if(roleOptional.isEmpty()){
                Room room = new Room();
                BeanUtils.copyProperties(roleOptional, room);
                Room roomSaved = roomRepository.save(room);
                return new RoomDto(room);
            }
            throw new RuntimeException("Error while retrieving role by id: " + id);
    }
    public RoomDto create(RoomDto roomDto){
            Room room = new Room();
            BeanUtils.copyProperties(roomDto, room);
            Room roomSaved = roomRepository.save(room);
            if(roomSaved != null){
                return new RoomDto(roomSaved);
            }
            throw new NotFoundException("Error while creating user");
    }
    public RoomDto updateById(RoomDto request){
            Optional<Room> roomOptional = roomRepository.findById(request.getId());
            if (roomOptional.isEmpty()){
                Room room = new Room();
                BeanUtils.copyProperties(roomOptional,room );
                Room roomSaved = roomRepository.save(room);
                return new RoomDto(roomSaved);
            }
            throw new NotFoundException("Data has not database table Room");
    }
    public boolean deleteById(Long id) throws NotFoundException {
            if (roomRepository.existsById(id)) {
                roomRepository.deleteById(id);
                return true;
            } else {
                throw new NotFoundException("User not found with id: " + id);
            }
    }
}

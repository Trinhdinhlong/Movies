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
        try {
            List<Room> rooms = roomRepository.findAll();
            System.out.println(rooms);
            return rooms.stream()
                    .map(RoomDto::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving all roles", e);
        }
    }
    public RoomDto getById(Long id) {
        try {
            Optional<Room> roleOptional = roomRepository.findById(id);
            Room room = roleOptional.orElseThrow(() -> new NotFoundException("Role not found with id: " + id));
            return new RoomDto(room);
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving role by id: " + id, e);
        }
    }
    public RoomDto create(RoomDto roomDto) throws NotFoundException {
        try {
            Room room = new Room();
            if (roomDto != null) {
                BeanUtils.copyProperties(roomDto, room);
            }
            Room roomSaved = roomRepository.save(room);
            return new RoomDto(roomSaved);
        } catch (Exception e) {
            throw new NotFoundException("Error while creating user");
        }
    }
    public RoomDto updateById(RoomDto request) throws BadRequestException, NotFoundException {
        if(request.getId() == null){
            throw new BadRequestException("Data was not found because id null", "NOT FOUND");
        }
        try {
            Optional<Room> roomOptional = roomRepository.findById(request.getId());
            Room room = roomOptional.orElseThrow(() -> new NotFoundException("Data id has not database"));
            BeanUtils.copyProperties(request, room);
            Room roomSaved = roomRepository.save(room);
            return new RoomDto(roomSaved);
        } catch (Exception e) {
            throw new NotFoundException("Data has not database table Room");
        }
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

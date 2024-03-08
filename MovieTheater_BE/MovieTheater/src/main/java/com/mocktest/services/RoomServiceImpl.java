package com.mocktest.services;

import com.mocktest.dto.RoomDto;
import com.mocktest.entities.Room;
import com.mocktest.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class RoomServiceImpl{
    @Autowired
    private RoomRepository roomRepository;
    public List<RoomDto> getAll() {
        try {
            List<Room> rooms = roomRepository.findAll();
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
            Room room = roleOptional.orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
            return new RoomDto(room);
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving role by id: " + id, e);
        }
    }
    public RoomDto create(RoomDto roomDto) {
        try {
            Room room = new Room();
            if (roomDto != null) {
                BeanUtils.copyProperties(roomDto, room);
            }
            Room roomSaved = roomRepository.save(room);
            return new RoomDto(roomSaved);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating user", e);
        }
    }
    public RoomDto updateById(RoomDto roomDto, Long id) {
        try {
            Optional<Room> roomOptional = roomRepository.findById(id);
            Room room = roomOptional.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
            if (roomDto != null) {
                BeanUtils.copyProperties(roomDto, room);
            }
            Room roomSaved = roomRepository.save(room);
            return new RoomDto(roomSaved);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating user with id: " + id, e);
        }
    }
    public boolean deleteById(Long id) {
        try {
            if (roomRepository.existsById(id)) {
                roomRepository.deleteById(id);
                return true;
            } else {
                throw new EntityNotFoundException("User not found with id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting user with id: " + id, e);
        }
    }
}

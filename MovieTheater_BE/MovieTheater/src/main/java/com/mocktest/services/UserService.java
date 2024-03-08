package com.mocktest.services;

import com.mocktest.dto.RoleDto;
import com.mocktest.dto.UserDto;
import com.mocktest.entities.Role;
import com.mocktest.entities.User;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Constants;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.core.Constants.*;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final RoleService roleService;

    public List<UserDto> getAll() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(UserDto::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving all users", e);
        }
    }

    public UserDto getById(Long id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            User user = userOptional.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
            return new UserDto(user);
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving user by id: " + id, e);
        }
    }

    public UserDto create(UserDto request) throws BadRequestException, NotFoundException {

        if(request == null){
            throw new BadRequestException("All fields are required.");
        }
        try {
            RoleDto roleDto = roleService.getById(2L);
            Role role = new Role();
            BeanUtils.copyProperties(roleDto, role);
            request.setRole(role);
            User user = new User();
            BeanUtils.copyProperties(request, user);
            User userSaved = userRepository.save(user);
            return new UserDto(userSaved);
        } catch (Exception e) {
            throw new NotFoundException("Email, IdentityCard or Number phone has Constraint");
        }
    }


    public UserDto updateById(UserDto userDto, Long id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            User user = userOptional.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
            if (userDto != null) {
                BeanUtils.copyProperties(userDto, user);
            }
            User userUpdated = userRepository.save(user);
            return new UserDto(userUpdated);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating user with id: " + id, e);
        }
    }


    public boolean deleteById(Long id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return true;
            } else {
                throw new EntityNotFoundException("User not found with id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting user with id: " + id, e);
        }
    }


    public UserDto getByUserName(UserDto userDto) {
        if(userDto.getUsername() != null){
            User user = userRepository.getByUsername(userDto);
            System.out.println();
            return new UserDto(user);
        }else {
            throw new EntityNotFoundException("User not found with username: " + userDto.getUsername());
        }
    }
    public UserDto login(UserDto userDto) throws BadRequestException, AuthenticationException {
        if(userDto.getUsername() == null && userDto.getPassword() == null){
            throw new BadRequestException("Not found data is null");
        }
        UserDto user = getByUserName(userDto);
        if (userDto.getUsername().equals(user.getUsername())
                && userDto.getPassword().equals(user.getPassword())) {
            return userDto;
        }else {
            throw new AuthenticationException("Account or password is not correct");
        }
    }
    public UserDto updateByUserName(UserDto userDto) throws BadRequestException, NotFoundException {
        if(userDto == null){
            throw new BadRequestException("Not found data is Null");
        }
        User user = userRepository.getByUsername(userDto);
        if (user != null) {
                userRepository.updateByUserName(userDto);
                return userDto;
        }else{
            throw new NotFoundException("User not found with username: " + userDto.getUsername());
        }
    }
}

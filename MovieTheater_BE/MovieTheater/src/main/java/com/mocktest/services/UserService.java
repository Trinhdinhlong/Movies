package com.mocktest.services;

import com.mocktest.dto.RoleDto;
import com.mocktest.dto.UserDto;
import com.mocktest.entities.Role;
import com.mocktest.entities.User;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.UserRepository;
import com.mocktest.exceptions.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
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
    public UserDto getById(UserDto request) throws NotFoundException {
        try {
            Optional<User> userOptional = userRepository.findById(request.getUserId());
            User user = userOptional.orElseThrow(() -> new NotFoundException("User not found with id"));
            return new UserDto(user);
        } catch (Exception e) {
            throw new NotFoundException("Error while retrieving user by id");
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
    public UserDto updateById(UserDto request) throws BadRequestException, NotFoundException {
        if (request == null){
            throw new BadRequestException("Data is null");
        }
        try {
            Optional<User> userOptional = userRepository.findById(request.getUserId());
            User user = userOptional.orElseThrow(() -> new NotFoundException("User not found with id: " + request.getUserId()));
            User userUpdated = userRepository.save(user);
            return new UserDto(userUpdated);
        } catch (Exception e) {
            throw new NotFoundException("Error while updating user with id: ");
        }
    }
    public boolean deleteById(UserDto request) throws NotFoundException {
        if (userRepository.existsById(request.getUserId())) {
            userRepository.deleteById(request.getUserId());
            return true;
        }else {
            throw new NotFoundException("data not found in entity User: " + request.getUserId());
        }
    }


    public UserDto getByUserName(UserDto userDto) throws NotFoundException {
        if(userDto.getUsername() != null){
            User user = userRepository.getByUsername(userDto);
            return new UserDto(user);
        }else {
            throw new NotFoundException("User not found with username: " + userDto.getUsername());
        }
    }
    public UserDto login(UserDto userDto) throws BadRequestException, AuthenticationException, NotFoundException {
        if(userDto.getUsername() == null || userDto.getPassword() == null){
            throw new BadRequestException("Not found data is null");
        }
        UserDto user = getByUserName(userDto);

        if (user == null) {
            throw new NotFoundException("User not found");
        }
        if (user.getPassword() == null && user.getUsername() == null) {
            throw new BadRequestException("Password is null for user: " + user.getUsername());
        }
        if (Objects.equals(userDto.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new AuthenticationException("Pass word is not correct");
        }
    }
}

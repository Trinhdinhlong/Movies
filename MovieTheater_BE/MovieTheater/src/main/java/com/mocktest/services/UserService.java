package com.mocktest.services;

import com.mocktest.dto.UserDto;
import com.mocktest.entities.User;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.MethodArgumentNotValidException;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.UserRepository;
import com.mocktest.exceptions.AuthenticationException;
import com.mocktest.until.PasswordEncoderExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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
    public UserDto create(@Valid UserDto request) throws BadRequestException, MethodArgumentNotValidException {
        if(request == null){
            throw new BadRequestException("All fields are required.", "NOT FOUND");
        }
        if(!PasswordEncoderExample.isValidPassword(request.getPassword())){
            throw new BadRequestException("Password does not meet the requirements.", "BAD_REQUEST");
        }
        try {
            User user = new User();
            BeanUtils.copyProperties(request, user);
            user.setPassword(PasswordEncoderExample.encode(user.getPassword()));
            User userSaved = userRepository.save(user);
            return new UserDto(userSaved);
        } catch (Exception e) {
            throw new MethodArgumentNotValidException("Email, IdentityCard or Number phone has Constraint, Email or Phone, IdentityCard is not format", "BAD_REQUEST");
        }
    }
    public UserDto updateById(UserDto request) throws BadRequestException, NotFoundException {
        if (request == null){
            throw new BadRequestException("Data is null", "NOT FOUND");
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
    public UserDto deleteById(UserDto request) throws NotFoundException {
        if (userRepository.existsById(request.getUserId())) {
            userRepository.deleteById(request.getUserId());
            return request;
        }else {
            throw new NotFoundException("data not found in entity User: " + request.getUserId());
        }
    }


    public UserDto getByUserName(UserDto request) throws NotFoundException {
        if(request.getUsername() != null){
            User user = userRepository.getByUsername(request);
            return new UserDto(user);
        }else {
            throw new NotFoundException("User not found with username: " + request.getUsername());
        }
    }
    public UserDto login(UserDto request) throws BadRequestException, AuthenticationException, NotFoundException {
        if(request.getUsername() == null || request.getPassword() == null){
            throw new BadRequestException("Not found data is null", "NOT FOUND");
        }
        UserDto user = getByUserName(request);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        if (user.getPassword() == null && user.getUsername() == null) {
            throw new BadRequestException("Password is null for user: " + user.getUsername(), "NOT FOUND");
        }
        if (PasswordEncoderExample.checkpw(request.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new AuthenticationException("Pass word is not correct");
        }
    }
}

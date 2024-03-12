package com.mocktest.services;

import com.mocktest.dto.UserDto;
import com.mocktest.entities.User;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.UserRepository;
import com.mocktest.until.PasswordEncoderExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) {
            throw new NotFoundException("No data!");
        }else{
            return users.stream()
                    .map(UserDto::new)
                    .collect(Collectors.toList());
        }
    }
    public UserDto getById(UserDto request){
            Optional<User> userOptional = userRepository.findById(request.getUserId());
            if(userOptional.isEmpty()){
                throw new NotFoundException("AAAA");
            }else {
                User user = userOptional.orElseThrow(() -> new NotFoundException("User not found with id"));
                return new UserDto(user);
            }
    }
    public UserDto create(UserDto request){
        if(!PasswordEncoderExample.isValidPassword(request.getPassword())){
            throw new BadRequestException("Password does not meet the requirements.");
        }
            User user = new User();
            BeanUtils.copyProperties(request, user);
            user.setPassword(PasswordEncoderExample.encode(user.getPassword()));
            User userSaved = userRepository.save(user);
            return new UserDto(userSaved);
    }
    public UserDto updateById(UserDto request){
            Optional<User> userOptional = userRepository.findById(request.getUserId());
            if(userOptional.isEmpty()){
                throw new NotFoundException("AAA");
            }else{
                User userUpdated = new User();
                BeanUtils.copyProperties(userOptional, userUpdated);
                userUpdated = userRepository.save(userUpdated);
                return new UserDto(userUpdated);
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
    public UserDto login(UserDto request){
        UserDto user = getByUserName(request);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        if (user.getPassword() == null && user.getUsername() == null) {
            throw new NotFoundException("Password is null for user: " + user.getUsername());
        }
        if (PasswordEncoderExample.checkPassword(request.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new BadRequestException("Pass word is not correct");
        }
    }
}

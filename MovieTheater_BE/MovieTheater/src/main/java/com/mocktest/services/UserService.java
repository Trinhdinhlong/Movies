package com.mocktest.services;

import com.mocktest.dto.UserDto;
import com.mocktest.entities.ShowTime;
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
public class UserService{
    @Autowired
    private UserRepository userRepository;
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
    public User getById(Long id){
            Optional<User> userOptional = userRepository.findById(id);
            User requests = userOptional.orElseThrow(() -> new NotFoundException("User not found with id"));
            return  requests;
    }
    public UserDto create(UserDto request){
        if(!PasswordEncoderExample.isValidPassword(request.getPassword())){
            throw new BadRequestException("Password does not meet the requirements.");
        }
        User requests = new User();
        BeanUtils.copyProperties(request, requests);
        requests.setPassword(PasswordEncoderExample.encode(requests.getPassword()));
        return new UserDto(userRepository.save(requests));
    }
    public UserDto updateById(UserDto request){
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        User requests = userOptional.orElseThrow(() -> new NotFoundException("User not found with id"));
        requests.setPassword(PasswordEncoderExample.encode(request.getPassword()));
        requests.setFullName(request.getFullName());
        requests.setDateOfBirth(request.getDateOfBirth());
        requests.setGender(request.getGender());
        requests.setEmail(request.getEmail());
        requests.setAddress(request.getAddress());
        requests.setPhone(request.getPhone());
        requests.setIdentityCard(request.getIdentityCard());
        requests.setImageURL(request.getImageURL());
        User user = userRepository.save(requests);
        UserDto response =  new UserDto();
        BeanUtils.copyProperties(requests, user);
        return response;
    }
    public void deleteById(Long request) {
        if (userRepository.existsById(request)) {
            User user = userRepository.getById(request);
            user.setActive("false");
            userRepository.save(user);
        }else {
            throw new NotFoundException("data not found in entity User: " + request);
        }
    }
    public UserDto getByUserName(String request) {
        if(request != null){
           return new UserDto(userRepository.getUserByUsername(request));

        }else {
            throw new NotFoundException("User not found with username: " + request);
        }
    }
    public UserDto login(UserDto request){
        UserDto user = getByUserName(request.getUsername());
        if (user.getPassword() == null && user.getUsername() == null &&
                !PasswordEncoderExample.checkpw(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Password is null for user: " + user.getUsername());
        }
        return user;
    }


}

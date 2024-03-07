package com.mocktest.services.impls;

import com.mocktest.dto.UserDto;
import com.mocktest.entities.User;
import com.mocktest.repository.UserRepository;
import com.mocktest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
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

    @Override
    public UserDto getById(Long id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            User user = userOptional.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
            return new UserDto(user);
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving user by id: " + id, e);
        }
    }

    @Override
    public UserDto create(UserDto userDto) {
        try {
            User user = new User();
            if (userDto != null) {
                BeanUtils.copyProperties(userDto, user);
            }
            User userSaved = userRepository.save(user);
            return new UserDto(userSaved);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating user", e);
        }
    }

    @Override
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

    @Override
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

    @Override
    public UserDto getByUserName(String username) {
        if(username != null){
            User user = userRepository.getByUsername(username);
            return new UserDto(user);
        }else {
            throw new EntityNotFoundException("User not found with username: " + username);
        }
    }

    @Override
    public UserDto updateByUserName(UserDto userDto) {
        User user = userRepository.getByUsername(userDto.getUsername());
        if (user != null) {
                userRepository.updateByUserName(userDto);
                return userDto;
        }else{
            throw new EntityNotFoundException("User not found with username: " + userDto.getUsername());
        }
    }
}

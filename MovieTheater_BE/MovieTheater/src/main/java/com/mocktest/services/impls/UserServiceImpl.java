package com.mocktest.services.impls;

import com.mocktest.dto.UserDto;
import com.mocktest.entities.User;
import com.mocktest.repository.UserRepository;
import com.mocktest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDto::new).toList();
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return new UserDto(userRepository.save(user));

    }

    @Override
    public UserDto updateById(UserDto userDto, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Team with ID %s not found.", id)));
        BeanUtils.copyProperties(userDto, user);
        return new UserDto(userRepository.save(user));
    }

    @Override
    public boolean deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Team with ID %s not found.", id)));
        userRepository.delete(user);
        return true;
    }
}

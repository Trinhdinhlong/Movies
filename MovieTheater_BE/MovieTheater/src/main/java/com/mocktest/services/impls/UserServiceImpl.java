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
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return new UserDto(user);
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = new User();
        if (userDto != null) {
            BeanUtils.copyProperties(userDto, user);
        }
        User userSaved = userRepository.save(user);
        return new UserDto(userSaved);

    }

}

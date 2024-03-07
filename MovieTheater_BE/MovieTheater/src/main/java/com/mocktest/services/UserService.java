package com.mocktest.services;

import com.mocktest.dto.UserDto;
import com.mocktest.entities.User;

public interface UserService extends BaseService<UserDto> {
    UserDto getByUserName(String username);
    UserDto updateByUserName(UserDto userDto);
}

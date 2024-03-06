package com.mocktest.Json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mocktest.dto.UserDto;

public interface UserJson {
    String ToJson(Long id, UserDto userDto) throws JsonProcessingException;
    UserDto CreateUserDto(String json) throws JsonProcessingException;
}

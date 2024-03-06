package com.mocktest.Json.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mocktest.dto.RoleDto;
import com.mocktest.dto.UserDto;
import com.mocktest.entities.Role;
import com.mocktest.Json.UserJson;
import com.mocktest.payload.ResponseData;
import com.mocktest.services.RoleService;
import com.mocktest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

@RequiredArgsConstructor
public class UserJsonImpl implements UserJson {
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final RoleService roleService;
    @Override
    public String ToJson(Long id, UserDto userDto) throws JsonProcessingException {
        UserDto userDto1 = userService.getById(id);
        if(userDto.getUsername().equals(userDto1.getUsername()) || userDto.getPassword().equals(userDto1.getPassword()))
        {
            ResponseData responseData = ResponseData.builder()
                    .isSuccess(true)
                    .data(userDto)
                    .build();
            return objectMapper.writeValueAsString(responseData);
        }else {
            return "Account or password is incorrect";
        }
    }

    @Override
    public UserDto CreateUserDto(String json) throws JsonProcessingException {
        UserDto userDto = objectMapper.readValue(json, UserDto.class);
        RoleDto roleDto = roleService.getById(2L);
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        userDto.setRole(role);
        return userDto;
    }

}

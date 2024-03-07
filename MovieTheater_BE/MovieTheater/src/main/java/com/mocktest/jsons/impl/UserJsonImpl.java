package com.mocktest.jsons.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mocktest.dto.RoleDto;
import com.mocktest.dto.UserDto;
import com.mocktest.entities.Role;
import com.mocktest.entities.User;
import com.mocktest.jsons.UserJson;
import com.mocktest.payload.ResponseData;
import com.mocktest.services.RoleService;
import com.mocktest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserJsonImpl implements UserJson {
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final RoleService roleService;


    @Override
    public String ToJson(String username, String password) throws JsonProcessingException {
        UserDto userDto = userService.getByUserName(username);
        if (userDto != null) {
            if (userDto.getUsername().equals(username) && userDto.getPassword().equals(password)) {
                ResponseData responseData = ResponseData.builder()
                        .isSuccess(true)
                        .data(userDto)
                        .build();
                return objectMapper.writeValueAsString(responseData);
            } else {
                return "Account or password is incorrect";
            }
        }
        return "Account or password is incorrect";
    }

    @Override
    public ResponseData CreateUser(String json) throws JsonProcessingException {
        UserDto userDto = objectMapper.readValue(json, UserDto.class);
        RoleDto roleDto = roleService.getById(2L);
        System.out.println(roleDto);
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        userDto.setRole(role);
        return ResponseData.builder()
                .isSuccess(true)
                .data(userService.create(userDto))
                .build();
    }

    @Override
    public ResponseData UpdateUser(String json) throws JsonProcessingException {
        UserDto userDto = objectMapper.readValue(json, UserDto.class);
        System.out.println(userDto);
        UserDto userDtoSaved = userService.updateByUserName(userDto);
        return ResponseData.builder()
                .isSuccess(true)
                .data(userDtoSaved)
                .build();
    }

}

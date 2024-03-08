package com.mocktest.jsons.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mocktest.dto.RoleDto;
import com.mocktest.dto.UserDto;
import com.mocktest.entities.Role;
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
    private final RoleService roleService;


    @Override
    public ResponseData ToJson(String username, String password) throws JsonProcessingException {
        UserDto userDto = userService.getByUserName(username);
        if (userDto != null) {
            if (userDto.getUsername().equals(username) && userDto.getPassword().equals(password)) {
                return ResponseData.builder()
                        .isSuccess(true)
                        .data(userDto)
                        .build();
            }
        }
        return ResponseData.builder()
                .isSuccess(false)
                .message("Account Or Password fail")
                .build();
    }

    @Override
    public ResponseData CreateUser(UserDto userDto) throws JsonProcessingException {
        RoleDto roleDto = roleService.getById(2L);
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        userDto.setRole(role);
        return ResponseData.builder()
                .isSuccess(true)
                .data(userService.create(userDto))
                .build();
    }

    @Override
    public ResponseData UpdateUserAndAdmin(UserDto userDto) throws JsonProcessingException {
        UserDto userDtoSaved = userService.updateByUserName(userDto);
        return ResponseData.builder()
                .isSuccess(true)
                .data(userDtoSaved)
                .build();
    }

}

package com.mocktest.jsons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mocktest.dto.UserDto;
import com.mocktest.payload.ResponseData;

public interface UserJson {
    ResponseData checkLogin(String username,String password) throws JsonProcessingException;
    ResponseData CreateUser(UserDto userDto) throws JsonProcessingException;
    ResponseData UpdateUserAndAdmin(UserDto userDto) throws JsonProcessingException;
}

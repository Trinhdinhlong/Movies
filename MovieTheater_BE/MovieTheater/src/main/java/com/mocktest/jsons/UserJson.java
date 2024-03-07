package com.mocktest.jsons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mocktest.dto.UserDto;
import com.mocktest.payload.ResponseData;

public interface UserJson {
    String ToJson(String username,String password) throws JsonProcessingException;
    ResponseData CreateUser(String json) throws JsonProcessingException;
    ResponseData UpdateUserAndAdmin(String json) throws JsonProcessingException;
}

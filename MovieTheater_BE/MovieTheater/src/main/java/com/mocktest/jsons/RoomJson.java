package com.mocktest.jsons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mocktest.payload.ResponseData;

public interface RoomJson {
    ResponseData ToJson() throws JsonProcessingException;
}

package com.mocktest.exceptions;

public class JsonProcessingException extends RuntimeException{
    public JsonProcessingException(){
         super();
    }
    public JsonProcessingException(String message){
        super(message);
    }

}

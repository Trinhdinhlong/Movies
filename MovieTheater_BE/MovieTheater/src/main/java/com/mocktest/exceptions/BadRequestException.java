package com.mocktest.exceptions;

public class BadRequestException extends  Exception{
    public String error;

    public BadRequestException(String message, String error){
        super(message);
        this.error = error;
    }
}

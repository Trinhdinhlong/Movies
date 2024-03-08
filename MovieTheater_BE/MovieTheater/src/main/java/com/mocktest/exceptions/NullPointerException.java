package com.mocktest.exceptions;

public class NullPointerException extends RuntimeException{
    public NullPointerException(){
        super();
    }
    public NullPointerException(String message){
        super(message);
    }
}

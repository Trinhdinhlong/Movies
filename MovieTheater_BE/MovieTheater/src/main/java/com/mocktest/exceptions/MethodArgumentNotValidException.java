package com.mocktest.exceptions;

public class MethodArgumentNotValidException extends Exception{
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public MethodArgumentNotValidException(String message, String error){
       super(message);
       this.error = error;
   }
}

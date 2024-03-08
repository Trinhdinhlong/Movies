package com.mocktest.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException(String message) {
        super(message);
    }
}

package com.tdd.core.exeception;

public class InvalidDataRequestException extends RuntimeException {
    public InvalidDataRequestException(String msg) {
        super(msg);
    }
}

package com.tdd.catalog.exeception;

public class InvalidDataRequestException extends RuntimeException {
    public InvalidDataRequestException(String msg) {
        super(msg);
    }
}

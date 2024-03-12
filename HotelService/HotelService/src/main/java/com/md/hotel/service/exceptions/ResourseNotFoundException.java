package com.md.hotel.service.exceptions;

public class ResourseNotFoundException extends RuntimeException {

    public ResourseNotFoundException() {
        super("Resourde Not found!");
    }

    public ResourseNotFoundException(String str) {
        super(str);
    }
}

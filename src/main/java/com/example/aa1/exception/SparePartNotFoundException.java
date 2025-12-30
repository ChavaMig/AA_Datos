package com.example.aa1.exception;

public class SparePartNotFoundException extends Exception {

    public SparePartNotFoundException() {
        super("Spare part not found");
    }

    public SparePartNotFoundException(String message) {
        super(message);
    }
}

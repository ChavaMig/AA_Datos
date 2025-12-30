package com.example.aa1.exception;

public class TicketNotFoundException extends Exception {

    public TicketNotFoundException() {
        super("Ticket not found");
    }

    public TicketNotFoundException(String message) {
        super(message);
    }
}

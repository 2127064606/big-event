package org.example.bigevent.exception;

public class DuplicateDataException extends RuntimeException{

    public DuplicateDataException(String message) {
        super(message);
    }

    public DuplicateDataException() {
        super();
    }
}

package com.example.APIRollTheDice.APIRollTheDice_Backend.Exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }

}

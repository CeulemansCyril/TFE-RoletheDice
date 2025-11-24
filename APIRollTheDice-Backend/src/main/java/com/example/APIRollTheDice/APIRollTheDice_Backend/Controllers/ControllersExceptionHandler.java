package com.example.APIRollTheDice.APIRollTheDice_Backend.Controllers;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.AlreadyExistsException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.UserFriendException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.CatchError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.LocalTime;

@ControllerAdvice
public class ControllersExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<CatchError> handleUserAlreadyExistsException(AlreadyExistsException ex, HttpServletRequest request) {
        CatchError error = new CatchError(
                LocalDateTime.now(),
                409,
                "Conflict",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(409).body(error);
    }

    @ExceptionHandler(UserFriendException.class)
    public ResponseEntity<CatchError> handleUserFriendException(UserFriendException ex, HttpServletRequest request) {
        CatchError error = new CatchError(
                LocalDateTime.now(),
                400,
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CatchError> handleUserNotFoundException(NotFoundException ex, HttpServletRequest request) {
        CatchError error = new CatchError(
                LocalDateTime.now(),
                404,
                "Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(404).body(error);
    }
}

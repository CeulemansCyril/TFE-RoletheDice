package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class CatchError {
    private LocalDateTime time;
    private int status;
    private String error;
    private String message;
    private String path;

    public CatchError(LocalDateTime time, int status, String error, String message, String path) {
        this.time = time;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }


    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

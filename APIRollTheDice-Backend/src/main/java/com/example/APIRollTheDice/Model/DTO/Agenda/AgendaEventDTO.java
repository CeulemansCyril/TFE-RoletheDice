package com.example.APIRollTheDice.Model.DTO.Agenda;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class AgendaEventDTO {
    private Long id;

    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime reminderTime;

    private boolean reminderSent = false;

    private Integer reminderMinutesBefore;
    private Long idCreator;


    public AgendaEventDTO() {
    }
    public AgendaEventDTO(Long id, String title, String description, LocalDateTime startDate, LocalDateTime endDate,Long idCreator,
                          LocalDateTime reminderTime, boolean reminderSent, Integer reminderMinutesBefore) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idCreator=idCreator;
        this.reminderTime = reminderTime;
        this.reminderSent = reminderSent;
        this.reminderMinutesBefore = reminderMinutesBefore;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(Long idCreator) {
        this.idCreator = idCreator;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    public boolean isReminderSent() {
        return reminderSent;
    }

    public void setReminderSent(boolean reminderSent) {
        this.reminderSent = reminderSent;
    }

    public Integer getReminderMinutesBefore() {
        return reminderMinutesBefore;
    }

    public void setReminderMinutesBefore(Integer reminderMinutesBefore) {
        this.reminderMinutesBefore = reminderMinutesBefore;
    }
}

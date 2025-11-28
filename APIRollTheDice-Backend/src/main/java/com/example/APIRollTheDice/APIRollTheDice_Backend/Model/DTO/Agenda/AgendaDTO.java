package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Agenda;

import java.util.List;

public class AgendaDTO {
    private Long id;
    private List<Long> idParticipants;
    private Long idOwner;
    private List<Long> idAgendaEvents;
    private String title;


    public AgendaDTO() {
    }

    public AgendaDTO(Long id, List<Long> idParticipants, Long idOwner, List<Long> idAgendaEvents, String title) {
        this.id = id;
        this.idParticipants = idParticipants;
        this.idOwner = idOwner;
        this.idAgendaEvents = idAgendaEvents;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public List<Long> getIdParticipants() {
        return idParticipants;
    }

    public void setIdParticipants(List<Long> idParticipants) {
        this.idParticipants = idParticipants;
    }

    public List<Long> getIdAgendaEvents() {
        return idAgendaEvents;
    }

    public void setIdAgendaEvents(List<Long> idAgendaEvents) {
        this.idAgendaEvents = idAgendaEvents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

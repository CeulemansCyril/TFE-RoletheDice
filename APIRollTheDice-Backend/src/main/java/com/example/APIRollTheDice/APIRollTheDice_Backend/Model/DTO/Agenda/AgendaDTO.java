package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Agenda;

import java.util.List;

public class AgendaDTO {
    private Long id;
    private List<Long> idparticipants;
    private Long idOwner;
    private List<Long> idagendaEvents;
    private String title;


    public AgendaDTO() {
    }

    public AgendaDTO(Long id, List<Long> idparticipants, Long idOwner, List<Long> idagendaEvents, String title) {
        this.id = id;
        this.idparticipants = idparticipants;
        this.idOwner = idOwner;
        this.idagendaEvents = idagendaEvents;
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

    public List<Long> getIdparticipants() {
        return idparticipants;
    }

    public void setIdparticipants(List<Long> idparticipants) {
        this.idparticipants = idparticipants;
    }

    public List<Long> getIdagendaEvents() {
        return idagendaEvents;
    }

    public void setIdagendaEvents(List<Long> idagendaEvents) {
        this.idagendaEvents = idagendaEvents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

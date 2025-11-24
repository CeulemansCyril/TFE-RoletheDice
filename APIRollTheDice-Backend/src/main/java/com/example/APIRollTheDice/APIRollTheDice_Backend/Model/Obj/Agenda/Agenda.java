package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Agenda;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "Agenda")
@Builder
public class Agenda {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "agenda_participants",
            joinColumns = @JoinColumn(name = "agenda_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;


    @OneToMany(mappedBy = "agendaId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AgendaEvent> agendaEvents;

    @Column(nullable = false)
    private String title;



    public Agenda() {
    }

    public Agenda(Long id, List<User> participants, User owner, List<AgendaEvent> agendaEvents, String title) {
        this.id = id;
        this.participants = participants;
        this.owner = owner;
        this.agendaEvents = agendaEvents;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<AgendaEvent> getAgendaEvents() {
        return agendaEvents;
    }

    public void setAgendaEvents(List<AgendaEvent> agendaEvents) {
        this.agendaEvents = agendaEvents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

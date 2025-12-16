package com.example.APIRollTheDice.Model.Obj.Agenda;

import com.example.APIRollTheDice.Model.Obj.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;



import java.time.LocalDateTime;


@Entity
@Table(name = "AgendaEvent")
public class AgendaEvent {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonBackReference
    private User creator;

    @ManyToOne
    @JoinColumn(name = "agenda_id", nullable = false)
    @JsonBackReference
    private Agenda agenda;

    public AgendaEvent() {
    }

    public AgendaEvent(Long id, String title, String description, LocalDateTime startDate, LocalDateTime endDate,Agenda agenda,User creator) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.agenda = agenda;
        this.creator =creator;

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

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
}

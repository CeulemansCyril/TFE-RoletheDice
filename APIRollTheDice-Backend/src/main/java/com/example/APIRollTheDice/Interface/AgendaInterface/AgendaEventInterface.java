package com.example.APIRollTheDice.Interface.AgendaInterface;

import com.example.APIRollTheDice.Model.Obj.Agenda.AgendaEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgendaEventInterface extends JpaRepository<AgendaEvent, Long> {

    List<AgendaEvent> findAllByAgendaId_Id(Long agendaId);
    Optional<AgendaEvent> findById(Long id);


    void deleteById(Long id);
    void deleteAllByAgendaId_Id(Long id);


    boolean existsById(Long id);
}

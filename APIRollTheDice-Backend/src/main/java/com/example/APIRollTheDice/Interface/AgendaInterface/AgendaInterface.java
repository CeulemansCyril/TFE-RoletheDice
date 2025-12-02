package com.example.APIRollTheDice.Interface.AgendaInterface;

import com.example.APIRollTheDice.Model.Obj.Agenda.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgendaInterface extends JpaRepository<Agenda, Long> {

    List<Agenda> findAllByOwnerId(Long ownerId);
    Optional<Agenda> findById(Long id);

    void deleteById(Long id);
    void deleteAllByOwnerId(Long ownerId);

    boolean existsById(Long id);
}

package com.example.APIRollTheDice.Interface.AgendaInterface;

import com.example.APIRollTheDice.Model.Obj.Agenda.AgendaEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendaEventInterface extends JpaRepository<AgendaEvent, Long> {

    List<AgendaEvent> findAllByAgendaId_Id(Long agendaId);
    Optional<AgendaEvent> findById(Long id);
    List<AgendaEvent> findByReminderTimeBeforeAndReminderSentFalse(LocalDateTime time);


    void deleteById(Long id);
    void deleteAllByAgendaId_Id(Long id);


    boolean existsById(Long id);

    @Query("""
    SELECT e FROM AgendaEvent e
    WHERE e.reminderSent = false
    AND e.reminderTime IS NOT NULL
    ORDER BY e.reminderTime ASC
""")
    List<AgendaEvent> findNextReminder(Pageable pageable);
}

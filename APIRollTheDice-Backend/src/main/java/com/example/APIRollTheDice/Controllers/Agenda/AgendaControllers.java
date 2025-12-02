package com.example.APIRollTheDice.Controllers.Agenda;

import com.example.APIRollTheDice.Model.DTO.Agenda.AgendaDTO;
import com.example.APIRollTheDice.Model.DTO.Agenda.AgendaEventDTO;
import com.example.APIRollTheDice.Model.Obj.Agenda.Agenda;
import com.example.APIRollTheDice.Model.Obj.Agenda.AgendaEvent;
import com.example.APIRollTheDice.Service.Agenda.AgendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agenda")
public class AgendaControllers {

    private final AgendaService agendaService;

    public AgendaControllers(AgendaService agendaService) {
        this.agendaService = agendaService;
    }


    // ------------------- AGENDA -------------------------

    @PostMapping("/CreateAgenda")
    public ResponseEntity<AgendaDTO> CreateAgenda(@RequestBody AgendaDTO agendaDTO){
        Agenda  agenda = agendaService.CreateAgenda(agendaService.DTOToEntity(agendaDTO));
        return ResponseEntity.ok(agendaService.AgendaToDTO(agenda));
    }

    @PutMapping("/UpdateAgenda")
    public ResponseEntity<AgendaDTO> UpdateAgenda(@RequestBody AgendaDTO agendaDTO){
        Agenda  agenda = agendaService.UpdateAgenda(agendaService.DTOToEntity(agendaDTO));
        return ResponseEntity.ok(agendaService.AgendaToDTO(agenda));
    }

    @GetMapping("/GetAgendaById/{id}")
    public ResponseEntity<AgendaDTO> GetAgendaById(@PathVariable Long id){
        Agenda  agenda = agendaService.GetAgenda(id);
        return ResponseEntity.ok(agendaService.AgendaToDTO(agenda));
    }

    @DeleteMapping("/DeleteAgendaById/{id}")
    public ResponseEntity<String> DeleteAgendaById(@PathVariable Long id) {
        agendaService.DeleteAgenda(id);
        return ResponseEntity.ok("Agenda deleted successfully.");
    }


    // ------------------- EVENTS -------------------------

    @PostMapping("/CreateAgendaEvent")
    public ResponseEntity<AgendaEventDTO> CreateAgendaEvent(@RequestBody AgendaEventDTO agendaEventDTO){
        AgendaEvent  agendaEvent = agendaService.createAgendaEvent(agendaService.AgendaEventDTOToEntity(agendaEventDTO));
        return ResponseEntity.ok(agendaService.AgendaEventToDTO(agendaEvent));
    }

    @PutMapping("/UpdateAgendaEvent")
    public ResponseEntity<AgendaEventDTO> UpdateAgendaEvent(@RequestBody AgendaEventDTO agendaEventDTO) {
        AgendaEvent agendaEvent = agendaService.updateAgendaEvent(agendaService.AgendaEventDTOToEntity(agendaEventDTO));
        return ResponseEntity.ok(agendaService.AgendaEventToDTO(agendaEvent));
    }

    @GetMapping("/GetAgendaEventByAgendaId/{id}")
    public ResponseEntity<List<AgendaEventDTO>> GetAgendaEventByAgendaId(@PathVariable Long id) {

        List<AgendaEvent> events = agendaService.getAgendaEventsByAgendaId(id);

        List<AgendaEventDTO> dtos = events.stream()
                .map(agendaService::AgendaEventToDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/DeleteAgendaEventById/{id}")
    public ResponseEntity<String> DeleteAgendaEventById(@PathVariable Long id) {
        agendaService.deleteAgendaEvent(id);
        return ResponseEntity.ok("Agenda Event deleted successfully.");
    }






}

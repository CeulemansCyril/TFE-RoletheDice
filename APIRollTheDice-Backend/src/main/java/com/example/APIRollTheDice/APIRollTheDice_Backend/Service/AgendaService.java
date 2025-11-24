package com.example.APIRollTheDice.APIRollTheDice_Backend.Service;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.AgendaInterface.AgendaEventInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.AgendaInterface.AgendaInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.UserRepository;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Agenda.AgendaEventMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Agenda.AgendaMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Agenda.AgendaDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Agenda.AgendaEventDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Agenda.Agenda;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Agenda.AgendaEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {
    private final AgendaEventInterface agendaEventInterface;
    private final AgendaInterface agendaInterface;
    private final AgendaMapper agendaMapper;
    private final AgendaEventMapper agendaEventMapper;
    private final UserRepository userRepository;

    public AgendaService(AgendaEventInterface agendaEventInterface, AgendaInterface agendaInterface, AgendaMapper agendaMapper,
                         AgendaEventMapper agendaEventMapper,UserRepository userRepository
    ) {
         this.agendaEventInterface = agendaEventInterface;
         this.agendaInterface = agendaInterface;
         this.agendaEventMapper =agendaEventMapper;
         this.agendaMapper=agendaMapper;
         this.userRepository =userRepository;
    }

    public void CreateAgenda(Agenda agenda) {
        agendaInterface.save(agenda);
    }
    public void DeleteAgenda(Long id) {
        if (agendaInterface.existsById(id)) {
            agendaEventInterface.deleteAllByAgendaId_Id(id);
            agendaInterface.deleteById(id);
        } else {
            throw new NotFoundException("Agenda  not found");
        }
    }
    public Agenda GetAgenda(Long id) {
        return agendaInterface.findById(id).orElseThrow(() -> new NotFoundException("Agenda not found"));
    }



    public Agenda UpdateAgenda(Agenda agenda){
        Agenda existing = agendaInterface.findById(agenda.getId())
                .orElseThrow(() -> new NotFoundException("Agenda not found"));

        existing.setParticipants(agenda.getParticipants());
        existing.setAgendaEvents(agenda.getAgendaEvents());
        existing.setTitle(agenda.getTitle());


        return agendaInterface.save(existing);
    }

    public void DeleteAllAgendasByOwnerId(Long ownerId) {
        agendaEventInterface.deleteAllByAgendaId_Id(ownerId);
        agendaInterface.deleteAllByOwnerId(ownerId);
    }

    public boolean AgendaExists(Long id) {
        return agendaInterface.existsById(id);
    }

    public AgendaDTO AgendaToDTO(Agenda agenda){
        AgendaDTO agendaDTO = agendaMapper.toDTO(agenda);
        return agendaDTO;
    }

    public  Agenda DTOToEntity(AgendaDTO agendaDTO){
        Agenda agenda = agendaMapper.toEntity(agendaDTO);
        agenda.setAgendaEvents(agendaEventInterface.findAllById(agendaDTO.getIdagendaEvents()));
        agenda.setOwner(userRepository.findById(agendaDTO.getIdOwner()).get());
        agenda.setParticipants(userRepository.findAllById(agendaDTO.getIdparticipants()));

        return agenda;
    }

    /// //////////////////////////////////////////////////////////////////////////////////////////////////////
    /// //////////////////////////////////////////////////////////////////////////////////////////////////////



    public void createAgendaEvent(AgendaEvent agendaEvent) {
        agendaEventInterface.save(agendaEvent);
    }

    public void deleteAgendaEvent(Long id) {
        if (agendaEventInterface.existsById(id)) {
            agendaEventInterface.deleteById(id);
        } else {
            throw new NotFoundException("Agenda event not found");
        }
    }

    public List<AgendaEvent> getAgendaEventsByAgendaId(Long agendaId) {
        return agendaEventInterface.findAllByAgendaId_Id(agendaId);
    }

    public void updateAgendaEvent(AgendaEvent agendaEvent) {
        if (agendaEventInterface.existsById(agendaEvent.getId())) {
            agendaEventInterface.save(agendaEvent);
        } else {
            throw new NotFoundException("Agenda event not found");
        }
    }

    public AgendaEvent UpdateAgendaEvent(AgendaEvent agendaEvent){
        AgendaEvent existing = agendaEventInterface.findById(agendaEvent.getId())
                .orElseThrow(() -> new NotFoundException("AgendaEvent not found"));

        existing.setDescription(agendaEvent.getDescription());
        existing.setTitle(agendaEvent.getTitle());
        existing.setEndDate(agendaEvent.getEndDate());
        existing.setStartDate(agendaEvent.getStartDate());


        return agendaEventInterface.save(existing);
    }



    public void deleteAgendaEventById(Long id) {
        if (agendaEventInterface.existsById(id)) {
            agendaEventInterface.deleteById(id);
        } else {
            throw new NotFoundException("Agenda event not found");
        }
    }
    public void DeleteAllAgendaEventsByAgendaId(Long agendaId) {
        agendaEventInterface.deleteAllByAgendaId_Id(agendaId);
    }
    public boolean AgendaEventExists(Long id) {
        return agendaEventInterface.existsById(id);
    }


    public AgendaEventDTO AgendaEventToDTO(AgendaEvent agendaEvent){
        AgendaEventDTO agendaEventDTO = agendaEventMapper.toDTO(agendaEvent);
        return  agendaEventDTO;
    }

    public AgendaEvent DTOToEntity(AgendaEventDTO agendaEventDTO){
        AgendaEvent agendaEvent = agendaEventMapper.toEntity(agendaEventDTO);
        return agendaEvent;
    }




}

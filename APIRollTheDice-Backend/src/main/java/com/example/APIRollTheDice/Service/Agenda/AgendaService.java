package com.example.APIRollTheDice.Service.Agenda;

import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.AgendaInterface.AgendaEventInterface;
import com.example.APIRollTheDice.Interface.AgendaInterface.AgendaInterface;
import com.example.APIRollTheDice.Interface.User.UserRepository;
import com.example.APIRollTheDice.Mapper.Agenda.AgendaEventMapper;
import com.example.APIRollTheDice.Mapper.Agenda.AgendaMapper;
import com.example.APIRollTheDice.Model.DTO.Agenda.AgendaDTO;
import com.example.APIRollTheDice.Model.DTO.Agenda.AgendaEventDTO;
import com.example.APIRollTheDice.Model.Obj.Agenda.Agenda;
import com.example.APIRollTheDice.Model.Obj.Agenda.AgendaEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {
    private final AgendaEventInterface agendaEventInterface;
    private final AgendaInterface agendaInterface;
    private final AgendaMapper agendaMapper;
    private final AgendaEventMapper agendaEventMapper;
    private final AgendaReminderService agendaReminderService;
    private final UserRepository userRepository;

    public AgendaService(AgendaEventInterface agendaEventInterface, AgendaInterface agendaInterface, AgendaMapper agendaMapper,
                         AgendaEventMapper agendaEventMapper,UserRepository userRepository, AgendaReminderService agendaReminderService
    ) {
         this.agendaEventInterface = agendaEventInterface;
         this.agendaInterface = agendaInterface;
         this.agendaEventMapper =agendaEventMapper;
         this.agendaMapper=agendaMapper;
         this.userRepository =userRepository;
            this.agendaReminderService = agendaReminderService;
    }

    public Agenda CreateAgenda(Agenda agenda) {
       return agendaInterface.save(agenda);
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
        agenda.setAgendaEvents(agendaEventInterface.findAllById(agendaDTO.getIdAgendaEvents()));
        agenda.setOwner(userRepository.findById(agendaDTO.getIdOwner()).get());
        agenda.setParticipants(userRepository.findAllById(agendaDTO.getIdParticipants()));

        return agenda;
    }

    /// //////////////////////////////////////////////////////////////////////////////////////////////////////
    /// //////////////////////////////////////////////////////////////////////////////////////////////////////



    public AgendaEvent createAgendaEvent(AgendaEvent agendaEvent) {
        AgendaEvent savedEvent = agendaEventInterface.save(agendaEvent);
        agendaReminderService.scheduleNext();
        return agendaEventInterface.save(savedEvent);
    }

    public void deleteAgendaEvent(Long id) {
        if (agendaEventInterface.existsById(id)) {
            agendaEventInterface.deleteById(id);
            agendaReminderService.scheduleNext();
        } else {
            throw new NotFoundException("Agenda event not found");
        }
    }

    public List<AgendaEvent> getAgendaEventsByAgendaId(Long agendaId) {
        return agendaEventInterface.findAllByAgendaId_Id(agendaId);
    }

    public AgendaEvent updateAgendaEvent(AgendaEvent agendaEvent) {
        AgendaEvent existing = agendaEventInterface.findById(agendaEvent.getId())
                .orElseThrow(() -> new NotFoundException("AgendaEvent not found"));
        existing.setTitle(agendaEvent.getTitle());
        existing.setDescription(agendaEvent.getDescription());
        existing.setStartDate(agendaEvent.getStartDate());
        existing.setEndDate(agendaEvent.getEndDate());
        agendaReminderService.scheduleNext();
        return agendaEventInterface.save(existing);
    }

    public List<AgendaEvent> findAllAgendaEventById(List<Long> ids){
        return agendaEventInterface.findAllById(ids);
    }



    public void deleteAgendaEventById(Long id) {
        if (agendaEventInterface.existsById(id)) {
            agendaEventInterface.deleteById(id);
            agendaReminderService.scheduleNext();
        } else {
            throw new NotFoundException("Agenda event not found");
        }
    }
    public void DeleteAllAgendaEventsByAgendaId(Long agendaId) {
        agendaEventInterface.deleteAllByAgendaId_Id(agendaId);
            agendaReminderService.scheduleNext();
    }
    public boolean AgendaEventExists(Long id) {
        return agendaEventInterface.existsById(id);
    }


    public AgendaEventDTO AgendaEventToDTO(AgendaEvent agendaEvent){
        return   agendaEventMapper.toDTO(agendaEvent);
    }

    public AgendaEvent AgendaEventDTOToEntity(AgendaEventDTO agendaEventDTO){
        AgendaEvent agendaEvent = agendaEventMapper.toEntity(agendaEventDTO);
        agendaEvent.setCreator(userRepository.findById(agendaEventDTO.getId()).orElseThrow(()-> new NotFoundException("User not found")));
        return agendaEvent;
    }








}

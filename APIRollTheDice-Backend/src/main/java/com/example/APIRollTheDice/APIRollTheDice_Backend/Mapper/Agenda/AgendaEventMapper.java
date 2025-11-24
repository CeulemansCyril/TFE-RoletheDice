package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Agenda;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Agenda.AgendaEventDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Agenda.AgendaEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendaEventMapper {
    AgendaEventDTO toDTO(AgendaEvent agendaEvent);
    AgendaEvent toEntity(AgendaEventDTO agendaEventDTO);
}

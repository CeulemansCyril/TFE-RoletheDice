package com.example.APIRollTheDice.Mapper.Agenda;

import com.example.APIRollTheDice.Model.DTO.Agenda.AgendaEventDTO;
import com.example.APIRollTheDice.Model.Obj.Agenda.AgendaEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgendaEventMapper {
    AgendaEventDTO toDTO(AgendaEvent agendaEvent);
    AgendaEvent toEntity(AgendaEventDTO agendaEventDTO);
}

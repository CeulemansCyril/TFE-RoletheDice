package com.example.APIRollTheDice.Mapper.Agenda;

import com.example.APIRollTheDice.Model.DTO.Agenda.AgendaEventDTO;
import com.example.APIRollTheDice.Model.Obj.Agenda.AgendaEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgendaEventMapper {
    @Mapping(source = "creator.id", target = "idCreator")
    AgendaEventDTO toDTO(AgendaEvent agendaEvent);

    @Mapping(target = "creator.id", ignore = true)
    AgendaEvent toEntity(AgendaEventDTO agendaEventDTO);
}

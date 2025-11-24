package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Agenda;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Agenda.AgendaDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Agenda.Agenda;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Agenda.AgendaEvent;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgendaMapper {

    @Mapping(source = "owner.id", target = "idOwner")
    @Mapping(source = "participants",target = "idparticipants")
    @Mapping(source = "agendaEvents", target = "idagendaEvents")
    AgendaDTO toDTO(Agenda agenda);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "participants",ignore = true)
    @Mapping(target = "agendaEvents",ignore = true)
    Agenda toEntity(AgendaDTO agendaDTO);

    default List<Long> mapUsersToIds(List<User> users){
        if(users == null || users.isEmpty()) return null;
        return users.stream().map(User::getId).toList();
    }

    default List<Long> mapEventToIds(List<AgendaEvent> events){
        if(events == null || events.isEmpty()) return null;
        return events.stream().map(AgendaEvent::getId).toList();
    }
}

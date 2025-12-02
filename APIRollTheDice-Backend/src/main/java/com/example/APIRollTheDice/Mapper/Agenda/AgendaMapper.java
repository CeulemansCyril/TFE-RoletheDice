package com.example.APIRollTheDice.Mapper.Agenda;

import com.example.APIRollTheDice.Model.DTO.Agenda.AgendaDTO;
import com.example.APIRollTheDice.Model.Obj.Agenda.Agenda;
import com.example.APIRollTheDice.Model.Obj.Agenda.AgendaEvent;
import com.example.APIRollTheDice.Model.Obj.User.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgendaMapper {

    @Mapping(source = "owner.id", target = "idOwner")
    @Mapping(source = "participants",target = "idParticipants",qualifiedByName="mapUsersToIds")
    @Mapping(source = "agendaEvents", target = "idAgendaEvents", qualifiedByName="mapEventToIds")
    AgendaDTO toDTO(Agenda agenda);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "participants",ignore = true)
    @Mapping(target = "agendaEvents",ignore = true)
    Agenda toEntity(AgendaDTO agendaDTO);

    @Named("mapUsersToIds")
    default List<Long> mapUsersToIds(List<User> users){
        if(users == null || users.isEmpty()) return null;
        return users.stream().map(User::getId).toList();
    }

    @Named("mapEventToIds")
    default List<Long> mapEventToIds(List<AgendaEvent> events){
        if(events == null || events.isEmpty()) return null;
        return events.stream().map(AgendaEvent::getId).toList();
    }
}

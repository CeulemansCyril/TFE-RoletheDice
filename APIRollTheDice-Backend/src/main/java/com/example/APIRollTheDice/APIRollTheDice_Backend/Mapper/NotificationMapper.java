package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.NotificationDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationDTO toDTO(Notification notification);
    Notification toEntity(NotificationDTO dto);
}

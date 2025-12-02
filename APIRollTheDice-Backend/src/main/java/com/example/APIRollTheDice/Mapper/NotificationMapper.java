package com.example.APIRollTheDice.Mapper;

import com.example.APIRollTheDice.Model.DTO.NotificationDTO;
import com.example.APIRollTheDice.Model.Obj.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationDTO toDTO(Notification notification);
    Notification toEntity(NotificationDTO dto);
}

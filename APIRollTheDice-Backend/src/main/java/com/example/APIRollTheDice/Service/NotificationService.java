package com.example.APIRollTheDice.Service;

import com.example.APIRollTheDice.Exception.AlreadyExistsException;
import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.NotificationInterface;
import com.example.APIRollTheDice.Mapper.NotificationMapper;
import com.example.APIRollTheDice.Model.DTO.NotificationDTO;
import com.example.APIRollTheDice.Model.Obj.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationInterface notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationService(
            NotificationInterface notificationRepository,
            NotificationMapper notificationMapper
    ) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }


    public Notification createNotification(Notification notification) {
        if (notification.getId() != null &&
                notificationRepository.existsById(notification.getId())) {
            throw new AlreadyExistsException("Notification with this ID already exists");
        }
        return notificationRepository.save(notification);
    }


    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Notification not found"));
    }

    public List<Notification> getAllNotificationsByReceiverId(Long receiverId) {
        return notificationRepository.findAllByReceiverId(receiverId);
    }


    public List<Notification> getAllNotificationsByReceiverIdNotRead(Long receiverId) {
        return notificationRepository.findAllByReceiverIdAndReadFalse(receiverId);
    }


    public void setAllNotificationReadForOneUser(Long userId) {
        List<Notification> notifications =
                notificationRepository.findAllByReceiverId(userId);

        if (notifications.isEmpty()) {
            throw new NotFoundException("No notifications found for receiver");
        }

        notifications.forEach(notification -> notification.setRead(true));
        notificationRepository.saveAll(notifications);
    }


    public Notification markNotificationAsRead(Long id) {
        Notification notification = getNotificationById(id);
        notification.setRead(true);
        return notificationRepository.save(notification);
    }


    public void deleteNotificationById(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new NotFoundException("Notification not found");
        }
        notificationRepository.deleteById(id);
    }


    public void deleteAllNotificationsByReceiverId(Long receiverId) {
        if (!notificationRepository.existsByReceiverId(receiverId)) {
            throw new NotFoundException("No notifications found for receiver");
        }
        notificationRepository.deleteAllByReceiverId(receiverId);
    }


    public int countNotificationsByReceiverId(Long receiverId) {
        return notificationRepository.countByReceiverId(receiverId);
    }


    public NotificationDTO notificationToDTO(Notification notification) {
        return notificationMapper.toDTO(notification);
    }

    public Notification notificationDTOToEntity(NotificationDTO dto) {
        return notificationMapper.toEntity(dto);
    }

    public int countUnreadNotifications(Long receiverId) {
        return notificationRepository.countByReceiverIdAndReadFalse(receiverId);
    }

}

package com.example.APIRollTheDice.APIRollTheDice_Backend.Service;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.AlreadyExistsException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.NotificationInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.NotificationDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Notification;
import com.example.APIRollTheDice.APIRollTheDice_Backend.SseEmitterService.SseNotification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationInterface notificationRepository;
    private final SseNotification sseNotification;

    public NotificationService(NotificationInterface notificationRepository, SseNotification sseNotification) {
        this.sseNotification = sseNotification;
        this.notificationRepository = notificationRepository;
    }


    public void createNotification(Notification notification) {
        if (notificationRepository.existsById(notification.getId())) {
            throw new AlreadyExistsException("Notification with this ID already exists");
        }else {
           Notification notificationSave= notificationRepository.save(notification);
           sseNotification.sendNotification(notificationSave.getReceiverId(), NotificationDTO.from(notificationSave));
        }

    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Notification not found"));
    }

    public List<Notification> getAllNotificationsByReceiverId(Long receiverId) {
       List<Notification> notifications = notificationRepository.findAllByReceiverId(receiverId);
        if (notifications.isEmpty()) {
            throw new NotFoundException("No notifications found for receiver");
        }
        return notifications;
    }

    public List<Notification> getAllNotificationsByReceiverIdNotRead(Long receiverId) {
        List<Notification> notifications = notificationRepository.findAllByReceiverIdAndReadFalse(receiverId);
        if (notifications.isEmpty()) {
            throw new NotFoundException("No unread notifications found for receiver");
        }
        return notifications;
    }

    public void SetAllNotificationReadForOneUser(Long idUser) {
        List<Notification> notifications = notificationRepository.findAllByReceiverId(idUser);
        if (notifications.isEmpty()) {
            throw new NotFoundException("No notifications found for receiver");
        }
        for (Notification notification : notifications) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }

    }

    public void NotifycationRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
        sseNotification.sendNotification(notification.getReceiverId(), NotificationDTO.from(notification));
    }


    public void deleteNotificationById(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
        } else {
            throw new NotFoundException("Notification not found  ");
        }
    }

    public void deleteAllNotificationsByReceiverId(Long receiverId) {
        if (notificationRepository.existsByReceiverId(receiverId)) {
            notificationRepository.deleteAllByReceiverId(receiverId);
        } else {
            throw new NotFoundException("No notifications found for receiver   " );
        }
    }

    public int countNotificationsByReceiverId(Long receiverId) {
        if (notificationRepository.existsByReceiverId(receiverId)) {
            return notificationRepository.countByReceiverId(receiverId);
        } else {
            throw new NotFoundException("No notifications found for receiver   " );
        }
    }
    public boolean notificationExistsById(Long id) {
        return notificationRepository.existsById(id);
    }


}

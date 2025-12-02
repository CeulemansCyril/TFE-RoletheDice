package com.example.APIRollTheDice.Controllers;

import com.example.APIRollTheDice.Model.DTO.NotificationDTO;
import com.example.APIRollTheDice.Model.Obj.Notification;
import com.example.APIRollTheDice.Service.NotificationService;
import com.example.APIRollTheDice.SseEmitterService.SseNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/api/Notifications")
public class NotificationControllers {
    private final NotificationService notificationInterface;
    private final SseNotification sseNotification;


    @Autowired
    public NotificationControllers(NotificationService notificationInterface, SseNotification sseNotification) {
        this.notificationInterface = notificationInterface;
        this.sseNotification = sseNotification;
    }

    @GetMapping("/stream/{receiverId}")
    public SseEmitter streamNotifications(@PathVariable Long receiverId) {
        return sseNotification.createEmitter(receiverId);
    }



    @PostMapping("/createNotification")
    public void createNotification(@RequestBody NotificationDTO notification) {
        notificationInterface.createNotification(notificationInterface.NotificationDTOToEntity(notification));

        if(sseNotification.hasEmitter(notification.getReceiverId())) {
            sseNotification.sendNotification(notification.getReceiverId(), notification);
        }

    }
    @PutMapping("/setAllNotificationReadForOneUser/{idUser}")
    public void setAllNotificationReadForOneUser(@PathVariable Long idUser) {
        notificationInterface.SetAllNotificationReadForOneUser(idUser);
    }
    @GetMapping("/getNotificationById/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable Long id) {
        Notification notification = notificationInterface.getNotificationById(id);
        return ResponseEntity.ok(notificationInterface.NotificationToDTO(notification));
    }
    @GetMapping("/getAllNotificationsByReceiverId/{receiverId}")
    public ResponseEntity<List<NotificationDTO>> getAllNotificationsByReceiverId(@PathVariable Long receiverId) {
        List<Notification> notifications = notificationInterface.getAllNotificationsByReceiverId(receiverId);
        List<NotificationDTO> notificationDTOs = notifications.stream()
                .map(notificationInterface::NotificationToDTO)
                .toList();
        return ResponseEntity.ok(notificationDTOs);
    }
    @GetMapping("/getAllNotificationsByReceiverIdNotRead/{receiverId}")
    public ResponseEntity<List<NotificationDTO>> getAllNotificationsByReceiverIdNotRead(@PathVariable Long receiverId) {
        List<Notification> notifications = notificationInterface.getAllNotificationsByReceiverIdNotRead(receiverId);
        List<NotificationDTO> notificationDTOs = notifications.stream()
                .map(notificationInterface::NotificationToDTO)
                .toList();
        return ResponseEntity.ok(notificationDTOs);
    }
    @DeleteMapping("/deleteNotificationById/{id}")
    public void deleteNotificationById(@PathVariable Long id) {
        notificationInterface.deleteNotificationById(id);
    }
    @DeleteMapping("/deleteAllNotificationsByReceiverId/{receiverId}")
    public void deleteAllNotificationsByReceiverId(@PathVariable Long receiverId) {
        notificationInterface.deleteAllNotificationsByReceiverId(receiverId);
    }


}

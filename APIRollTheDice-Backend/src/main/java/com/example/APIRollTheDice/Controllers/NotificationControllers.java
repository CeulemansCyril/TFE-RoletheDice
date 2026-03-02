package com.example.APIRollTheDice.Controllers;

import com.example.APIRollTheDice.Model.DTO.NotificationDTO;
import com.example.APIRollTheDice.Model.Obj.Notification;
import com.example.APIRollTheDice.Service.NotificationService;
import com.example.APIRollTheDice.SseEmitterService.SseNotification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/api/Notifications")
public class NotificationControllers {

    private final NotificationService notificationService;
    private final SseNotification sseNotification;

    public NotificationControllers(
            NotificationService notificationService,
            SseNotification sseNotification
    ) {
        this.notificationService = notificationService;
        this.sseNotification = sseNotification;
    }


    @GetMapping(
            value = "/stream/{receiverId}",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public SseEmitter streamNotifications(@PathVariable Long receiverId) {
        return sseNotification.createEmitter(receiverId);
    }


    @PostMapping("/createNotification")
    public ResponseEntity<Void> createNotification(
            @RequestBody NotificationDTO notificationDTO
    ) {
        notificationService.createNotification(
                notificationService.notificationDTOToEntity(notificationDTO)
        );

        if (sseNotification.hasEmitter(notificationDTO.getReceiverId())) {
            sseNotification.sendNotification(
                    notificationDTO.getReceiverId(),
                    notificationDTO
            );
        }

        return ResponseEntity.ok().build();
    }


    @PutMapping("/setAllNotificationReadForOneUser/{userId}")
    public ResponseEntity<Void> setAllNotificationReadForOneUser(
            @PathVariable Long userId
    ) {
        notificationService.setAllNotificationReadForOneUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/read/{notificationId}")
    public ResponseEntity<NotificationDTO> markNotificationAsRead(
            @PathVariable Long notificationId
    ) {
        Notification notification =
                notificationService.markNotificationAsRead(notificationId);

        NotificationDTO dto =
                notificationService.notificationToDTO(notification);


        if (sseNotification.hasEmitter(notification.getReceiverId())) {
            sseNotification.sendNotification(notification.getReceiverId(), dto);
        }

        return ResponseEntity.ok(dto);
    }



    @GetMapping("/getNotificationById/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(
            @PathVariable Long id
    ) {
        Notification notification = notificationService.getNotificationById(id);
        return ResponseEntity.ok(
                notificationService.notificationToDTO(notification)
        );
    }


    @GetMapping("/getAllNotificationsByReceiverId/{receiverId}")
    public ResponseEntity<List<NotificationDTO>> getAllNotificationsByReceiverId(
            @PathVariable Long receiverId
    ) {
        List<NotificationDTO> notificationDTOs =
                notificationService.getAllNotificationsByReceiverId(receiverId)
                        .stream()
                        .map(notificationService::notificationToDTO)
                        .toList();

        return ResponseEntity.ok(notificationDTOs);
    }


    @GetMapping("/getAllNotificationsByReceiverIdNotRead/{receiverId}")
    public ResponseEntity<List<NotificationDTO>> getAllNotificationsByReceiverIdNotRead(
            @PathVariable Long receiverId
    ) {
        List<NotificationDTO> notificationDTOs =
                notificationService.getAllNotificationsByReceiverIdNotRead(receiverId)
                        .stream()
                        .map(notificationService::notificationToDTO)
                        .toList();

        return ResponseEntity.ok(notificationDTOs);
    }




    @DeleteMapping("/deleteNotificationById/{id}")
    public ResponseEntity<Void> deleteNotificationById(
            @PathVariable Long id
    ) {
        notificationService.deleteNotificationById(id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/deleteAllNotificationsByReceiverId/{receiverId}")
    public ResponseEntity<Void> deleteAllNotificationsByReceiverId(
            @PathVariable Long receiverId
    ) {
        notificationService.deleteAllNotificationsByReceiverId(receiverId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/count/unread/{receiverId}")
    public ResponseEntity<Integer> countUnreadNotifications(
            @PathVariable Long receiverId
    ) {
        int count = notificationService.countUnreadNotifications(receiverId);
        return ResponseEntity.ok(count);
    }

}

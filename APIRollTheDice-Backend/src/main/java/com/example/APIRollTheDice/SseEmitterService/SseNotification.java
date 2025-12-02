package com.example.APIRollTheDice.SseEmitterService;

import com.example.APIRollTheDice.Model.DTO.NotificationDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseNotification {

    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter createEmitter(Long receiverId) {
        SseEmitter emitter = new SseEmitter(0L);
        emitters.put(receiverId, emitter);
        emitter.onCompletion(() -> emitters.remove(receiverId));
        emitter.onTimeout(() -> emitters.remove(receiverId));
        return emitter;
    }

    public void sendNotification(Long receiverId, NotificationDTO message) {
        SseEmitter emitter = emitters.get(receiverId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().name("notification").data(message));
            } catch (Exception e) {
                emitters.remove(receiverId);
            }
        }
    }

    public void removeEmitter(Long receiverId) {
        emitters.remove(receiverId);
    }

    public boolean hasEmitter(Long receiverId) {
        return emitters.containsKey(receiverId);
    }

}

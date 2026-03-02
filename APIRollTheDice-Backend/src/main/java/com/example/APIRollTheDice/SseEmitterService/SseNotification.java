package com.example.APIRollTheDice.SseEmitterService;

import com.example.APIRollTheDice.Model.DTO.NotificationDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseNotification {

    private final Map<Long, Set<SseEmitter>> emitters = new ConcurrentHashMap<>();

    public SseEmitter createEmitter(Long receiverId) {
        SseEmitter emitter = new SseEmitter(0L);

        emitters
                .computeIfAbsent(receiverId, id -> ConcurrentHashMap.newKeySet())
                .add(emitter);

        emitter.onCompletion(() -> removeEmitter(receiverId, emitter));
        emitter.onTimeout(() -> removeEmitter(receiverId, emitter));
        emitter.onError(e -> removeEmitter(receiverId, emitter));

        return emitter;
    }

    public void sendNotification(Long receiverId, NotificationDTO message) {
        Set<SseEmitter> userEmitters = emitters.get(receiverId);
        if (userEmitters == null) return;

        userEmitters.forEach(emitter -> {
            try {
                emitter.send(
                        SseEmitter.event()
                                .name("notification")
                                .data(message)
                );
            } catch (Exception e) {
                removeEmitter(receiverId, emitter);
            }
        });
    }

    private void removeEmitter(Long receiverId, SseEmitter emitter) {
        Set<SseEmitter> userEmitters = emitters.get(receiverId);
        if (userEmitters != null) {
            userEmitters.remove(emitter);
            emitter.complete();

            if (userEmitters.isEmpty()) {
                emitters.remove(receiverId);
            }
        }
    }

    public boolean hasEmitter(Long receiverId) {
        return emitters.containsKey(receiverId);
    }

    @Scheduled(fixedRate = 30000)
    public void sendHeartbeat() {
        emitters.forEach((userId, userEmitters) -> {
            userEmitters.forEach(emitter -> {
                try {
                    emitter.send(SseEmitter.event().name("ping").data("keep-alive"));
                } catch (Exception e) {
                    emitter.complete();
                }
            });
        });
    }

}


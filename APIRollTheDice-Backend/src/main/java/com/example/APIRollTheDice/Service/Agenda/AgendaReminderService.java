package com.example.APIRollTheDice.Service.Agenda;

import com.example.APIRollTheDice.WebSocket.Core.WSSender;
import com.example.APIRollTheDice.WebSocket.Enum.WSMessageTypes;
import com.example.APIRollTheDice.WebSocket.Enum.WSScopeEnum;
import com.example.APIRollTheDice.Interface.AgendaInterface.AgendaEventInterface;
import com.example.APIRollTheDice.Model.Obj.Agenda.AgendaEvent;
import com.example.APIRollTheDice.Model.Obj.User.User;
import com.example.APIRollTheDice.Service.NotificationService;
import com.example.APIRollTheDice.WebSocket.GameWebSocketHandler;
import com.example.APIRollTheDice.WebSocket.DTO.WSMessage;
import jakarta.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class AgendaReminderService {

    private final AgendaEventInterface repo;
    private final WSSender wsSender;
    private final NotificationService notificationService;
    private final TaskScheduler scheduler;

    private ScheduledFuture<?> currentTask;

    public AgendaReminderService(
            AgendaEventInterface repo,
            WSSender wsSender,
            NotificationService notificationService,
            TaskScheduler scheduler
    ) {
        this.repo = repo;
        this.wsSender=wsSender;
        this.notificationService = notificationService;
        this.scheduler = scheduler;
    }


    @PostConstruct
    public void init() {
        scheduleNext();
    }


    public void scheduleNext() {


        if (currentTask != null) {
            currentTask.cancel(false);
        }

        AgendaEvent next = repo.findNextReminder(PageRequest.of(0,1))
                .stream()
                .findFirst()
                .orElse(null);

        if (next == null) return;

        currentTask = scheduler.schedule(
                () -> executeReminder(next),
                Timestamp.valueOf(next.getReminderTime())
        );
    }


    private void executeReminder(AgendaEvent event) {

        try {
            sendReminder(event);

            event.setReminderSent(true);
            repo.save(event);

        } catch (Exception e) {
            e.printStackTrace();
        }


        scheduleNext();
    }

  
    private void sendReminder(AgendaEvent event) throws Exception {

        WSMessage msg = new WSMessage();
        msg.setType(WSMessageTypes.AGENDA_REMINDER);
        msg.setScope(WSScopeEnum.AGENDA);

        msg.setPayload(Map.of(
                "eventId", event.getId(),
                "title", event.getTitle(),
                "message", "Rappel : " + event.getTitle()
        ));

        wsSender.sendToUser(event.getCreator().getId(), msg);
 
        for (User user : event.getAgenda().getParticipants()) {
            wsSender.sendToUser(user.getId(), msg);
        }


    }
}
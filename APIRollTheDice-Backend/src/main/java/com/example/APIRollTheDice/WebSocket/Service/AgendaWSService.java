package com.example.APIRollTheDice.WebSocket.Service;

import com.example.APIRollTheDice.Model.Obj.Agenda.Agenda;
import com.example.APIRollTheDice.Service.Agenda.AgendaService;
import com.example.APIRollTheDice.WebSocket.Core.WSSender;
import com.example.APIRollTheDice.WebSocket.DTO.WSMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AgendaWSService {
    private final WSSender handler;
    private final AgendaService agendaService;

    public AgendaWSService(WSSender handler, AgendaService agendaService) {
        this.handler = handler;
        this.agendaService = agendaService;
    }

    public void handle(WSMessage msg) throws Exception {
        switch (msg.getType()) {
            case AGENDA_CREATE:
                createAgenda(msg);
                break;
            case AGENDA_UPDATE:
                updateAgenda(msg);
                break;
            case AGENDA_DELETE:
                deleteAgenda(msg);
                break;

        }
    }

    private void createAgenda(WSMessage msg) throws Exception {
        Agenda agenda = handler.getFromPayload(msg, "agenda", Agenda.class);
        if (agenda == null) return;
        agenda = agendaService.CreateAgenda(agenda);
        msg.setPayload(Map.of("agenda", agenda));
        handler.sendToUser(msg.getGameId(), msg);
    }

    private void updateAgenda(WSMessage msg) throws Exception {
        Agenda updatedAgenda = handler.getFromPayload(msg, "agenda", Agenda.class);
        if (updatedAgenda == null) return;
        updatedAgenda = agendaService.UpdateAgenda(updatedAgenda);
        msg.setPayload(Map.of("agenda", updatedAgenda));
        handler.sendToUser(msg.getGameId(), msg);
    }

    private void deleteAgenda(WSMessage msg) throws Exception {
        Long agendaId = handler.getFromPayload(msg, "agendaId", Long.class);
        if (agendaId == null) return;
        agendaService.DeleteAgenda(agendaId);
        msg.setPayload(Map.of("agendaId", agendaId));
        handler.sendToUser(msg.getGameId(), msg);
    }



}

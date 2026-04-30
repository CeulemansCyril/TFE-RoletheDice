package com.example.APIRollTheDice.WebSocket.Service;

import com.example.APIRollTheDice.Model.Obj.Game.Game;
import com.example.APIRollTheDice.Service.Game.GameService;
import com.example.APIRollTheDice.WebSocket.Core.WSSender;
import com.example.APIRollTheDice.WebSocket.GameWebSocketHandler;
import com.example.APIRollTheDice.WebSocket.DTO.WSMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GameWSService {
    private final WSSender handler;
    private final GameService gameService;

    public GameWSService(WSSender handler, GameService gameService) {
        this.handler = handler;
        this.gameService = gameService;
    }

    public void handle(WSMessage msg) throws Exception {
        switch (msg.getType()) {
            case CREATED_GAME:
                CreateGame(msg);
                break;
            case DELETED_GAME:
                DeleteGame(msg);
                break;
            case UPDATED_GAME:
                UpdateGame(msg);
                break;

        }
    }

    private void CreateGame(WSMessage msg) throws Exception {
        Game game = handler.getFromPayload(msg, "game", Game.class);
        if (game == null) return;
        game = gameService.CreateGame(game);
        msg.setPayload(Map.of("game", game));
        handler.sendToUser(msg.getGameId(), msg);
    }

    private void DeleteGame(WSMessage msg) throws Exception {
        Long gameId = handler.getFromPayload(msg, "gameId", Long.class);
        if (gameId == null) return;
        gameService.DeleteGame(gameId);
        msg.setPayload(Map.of("gameId", gameId));
        handler.sendToUser(msg.getGameId(), msg);
    }

    private void UpdateGame(WSMessage msg) throws Exception {
        Game game = handler.getFromPayload(msg, "game", Game.class);
        if (game == null) return;
        game = gameService.UpdateGame(game);
        msg.setPayload(Map.of("game", game));
        handler.sendToUser(msg.getGameId(), msg);
    }


}

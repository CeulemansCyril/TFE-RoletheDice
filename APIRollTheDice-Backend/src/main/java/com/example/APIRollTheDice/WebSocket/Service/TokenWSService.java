package com.example.APIRollTheDice.WebSocket.Service;

import com.example.APIRollTheDice.Model.Obj.Game.Token.TokenPlaced;
import com.example.APIRollTheDice.Service.Game.Token.TokenService;
import com.example.APIRollTheDice.WebSocket.Core.WSSender;
import com.example.APIRollTheDice.WebSocket.DTO.WSMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenWSService {
    private final WSSender handler;
    private final TokenService tokenService;

    public TokenWSService(WSSender handler, TokenService tokenService) {
        this.handler = handler;
        this.tokenService = tokenService;
    }

    public void handle(WSMessage msg) throws Exception {
        switch (msg.getType()) {

            case TOKEN_MOVE:
                tokenMove(msg);
                break;

            case TOKEN_PLACE:
                tokenPlace(msg);
                break;

            case TOKEN_REMOVE:
                tokenRemove(msg);
                break;
        }
    }

    private void tokenMove(WSMessage msg) throws Exception {
        Map<String, Object> payload = msg.getPayload();
        if (payload == null) return;
        TokenPlaced tokenPlaced = handler.getFromPayload(msg, "tokenPlaced", TokenPlaced.class);
        tokenPlaced = tokenService.UpdateTokenPlaced(tokenPlaced);
        msg.setPayload(Map.of("tokenPlaced", tokenPlaced));
        handler.sendToRoom(msg.getGameId(), msg);
    }

    private void tokenPlace(WSMessage msg) throws Exception {
        Map<String, Object> payload = msg.getPayload();
        if (payload == null) return;
        TokenPlaced tokenPlaced = handler.getFromPayload(msg, "tokenPlaced", TokenPlaced.class);
        tokenPlaced = tokenService.CreateTokenPlaced(tokenPlaced);
        msg.setPayload(Map.of("tokenPlaced", tokenPlaced));
        handler.sendToRoom(msg.getGameId(), msg);
    }

    private void tokenRemove(WSMessage msg) throws Exception {
        Map<String, Object> payload = msg.getPayload();
        if (payload == null) return;
        Long idToken = handler.getFromPayload(msg, "tokenId", Long.class);
        tokenService.DeleteTokenPlaced(idToken);
        msg.setPayload(Map.of("tokenId", idToken));
        handler.sendToRoom(msg.getGameId(), msg);
    }


}

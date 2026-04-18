package com.example.APIRollTheDice.WebSocket.Object;

import com.example.APIRollTheDice.Enum.WSMessageTypes;
import com.example.APIRollTheDice.Model.Obj.Game.Token.TokenPlaced;

public class WSMessage {

    private WSMessageTypes type;
    private Long gameId;
    private Long playerId;

    private String content;

    private Object data;

    public WSMessage(WSMessageTypes type, Long gameId, Long playerId, String content, Object data) {
        this.type = type;
        this.gameId = gameId;
        this.playerId = playerId;
        this.content = content;
        this.data = data;

    }

    public WSMessage() {
    }

    public WSMessageTypes getType() {
        return type;
    }

    public void setType(WSMessageTypes type) {
        this.type = type;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

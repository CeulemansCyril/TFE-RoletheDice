package com.example.APIRollTheDice.WebSocket.DTO;

import com.example.APIRollTheDice.WebSocket.Enum.WSMessageTypes;
import com.example.APIRollTheDice.WebSocket.Enum.WSScopeEnum;

import java.util.Map;

public class WSMessage {

    private WSMessageTypes type;
    private WSScopeEnum scope;

    private Long gameId;
    private Long channelId;

    private Long senderUserId;
    private Long targetUserId;

    private Long playerId;

    private String content;

    private Map<String, Object> payload;

    public WSMessage() {
    }

    public WSMessage(WSMessageTypes type, WSScopeEnum scope, Long gameId, Long channelId, Long senderUserId, Long targetUserId, Map<String, Object> payload, String content, Long playerId) {
        this.type = type;
        this.scope = scope;
        this.gameId = gameId;
        this.channelId = channelId;
        this.senderUserId = senderUserId;
        this.targetUserId = targetUserId;
        this.payload = payload;
        this.content = content;
        this.playerId = playerId;
    }

    public WSMessageTypes getType() {
        return type;
    }

    public void setType(WSMessageTypes type) {
        this.type = type;
    }

    public WSScopeEnum getScope() {
        return scope;
    }

    public void setScope(WSScopeEnum scope) {
        this.scope = scope;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Long getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}

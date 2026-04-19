package com.example.APIRollTheDice.Enum;
public enum WSMessageTypes {

    // GAME
    PLAYER_JOINED,
    PLAYER_LEFT,

    // TOKEN
    TOKEN_MOVE,
    TOKEN_PLACE,
    TOKEN_REMOVE,

    // CHAT
    CHAT_MESSAGE,
    CREATE_CHANNEL,

    // PRIVATE
    PRIVATE_MESSAGE,

    // AGENDA
    AGENDA_CREATE,
    AGENDA_UPDATE,
    AGENDA_DELETE,
    AGENDA_REMINDER,

    // SYSTEM / NOTIF
    NOTIFICATION
}
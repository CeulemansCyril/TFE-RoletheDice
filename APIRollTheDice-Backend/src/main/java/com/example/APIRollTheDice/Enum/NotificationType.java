package com.example.APIRollTheDice.Enum;

public enum NotificationType {
    FRIEND_REQUEST("Friend Request"),
    FRIEND_REQUEST_ACCEPTED("Friend Request Accepted"),
    FRIEND_REQUEST_REJECTED("Friend Request Rejected"),
    USER_BLOCKED("User Blocked"),
    USER_UNBLOCKED("User Unblocked"),
    USER_PROFILE_UPDATED("User Profile Updated"),
    MESSAGE_RECEIVED("Message Received"),
    GAME_INVITATION("Game Invitation"),
    GAME_INVITATION_ACCEPTED("Game Invitation Accepted"),
    GAME_INVITATION_REJECTED("Game Invitation Rejected"),
    AGENDA_EVENT_CREATED("Agenda Event Created"),
    AGENDA_EVENT_UPDATED("Agenda Event Updated"),
    AGENDA_EVENT_DELETED("Agenda Event Deleted"),
    AGENDA_EVENT_REMINDER("Agenda Event Reminder"),
    AGENDA_INVITATION("Agenda Invitation");

    private final String description;

    NotificationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

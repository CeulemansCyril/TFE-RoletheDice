namespace Assets._Project.API.Enums
{
    using System.ComponentModel;

   public sealed class NotificationType
{
 
    public static readonly NotificationType FRIEND_REQUEST =
        new NotificationType("FRIEND_REQUEST", "Friend Request");

    public static readonly NotificationType FRIEND_REQUEST_ACCEPTED =
        new NotificationType("FRIEND_REQUEST_ACCEPTED", "Friend Request Accepted");

    public static readonly NotificationType FRIEND_REQUEST_REJECTED =
        new NotificationType("FRIEND_REQUEST_REJECTED", "Friend Request Rejected");

    public static readonly NotificationType USER_BLOCKED =
        new NotificationType("USER_BLOCKED", "User Blocked");

    public static readonly NotificationType USER_UNBLOCKED =
        new NotificationType("USER_UNBLOCKED", "User Unblocked");

    public static readonly NotificationType USER_PROFILE_UPDATED =
        new NotificationType("USER_PROFILE_UPDATED", "User Profile Updated");

    public static readonly NotificationType MESSAGE_RECEIVED =
        new NotificationType("MESSAGE_RECEIVED", "Message Received");

    public static readonly NotificationType GAME_INVITATION =
        new NotificationType("GAME_INVITATION", "Game Invitation");

    public static readonly NotificationType GAME_INVITATION_ACCEPTED =
        new NotificationType("GAME_INVITATION_ACCEPTED", "Game Invitation Accepted");

    public static readonly NotificationType GAME_INVITATION_REJECTED =
        new NotificationType("GAME_INVITATION_REJECTED", "Game Invitation Rejected");

    public static readonly NotificationType AGENDA_EVENT_CREATED =
        new NotificationType("AGENDA_EVENT_CREATED", "Agenda Event Created");

    public static readonly NotificationType AGENDA_EVENT_UPDATED =
        new NotificationType("AGENDA_EVENT_UPDATED", "Agenda Event Updated");

    public static readonly NotificationType AGENDA_EVENT_DELETED =
        new NotificationType("AGENDA_EVENT_DELETED", "Agenda Event Deleted");

    public static readonly NotificationType AGENDA_EVENT_REMINDER =
        new NotificationType("AGENDA_EVENT_REMINDER", "Agenda Event Reminder");

    public static readonly NotificationType AGENDA_INVITATION =
        new NotificationType("AGENDA_INVITATION", "Agenda Invitation");

 
    public string Name { get; }
    public string Description { get; }
 
    private NotificationType(string name, string description)
    {
        Name = name;
        Description = description;
    }

   
    public override string ToString() => Name;
}

}
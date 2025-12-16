using System;


using  RollTheDice.API.Models.Users;

 namespace RollTheDice.API.Models.Agenda
{
public class AgendaEvent
{
    public long EventId { get; set; }
    public string EventName { get; set; }
    public string Title { get; set; }
    public string Description { get; set; }
    public DateTime StartDate { get; set; }

    public DateTime EndDate { get; set; }

    private UserIdentifantData User { get; set; }

    public AgendaEvent() { }

    public AgendaEvent(long eventId, string eventName, string title, string description, DateTime startDate, DateTime endDate,  UserIdentifantData user)
    {
        EventId = eventId;
        EventName = eventName;
        Title = title;
        Description = description;
        StartDate = startDate;
        EndDate = endDate;
        User = user;
    }
}
}
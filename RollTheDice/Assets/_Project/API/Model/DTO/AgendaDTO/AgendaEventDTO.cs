using System;

namespace RollTheDice.API.Models.AgendaDTO
{
public class AgendaEventDTO
{
    public long EventId { get; set; }
    public string EventName { get; set; }
    public string Title { get; set; }
    public string Description { get; set; }
    public DateTime StartDate { get; set; }

    public DateTime EndDate { get; set; }

    public long IdCreator { get; set; }

    public AgendaEventDTO(){}

    public AgendaEventDTO(long id,long idCreator, string eventName, string title, string description, DateTime startDate, DateTime endDate)
    {
        EventId = id;
        IdCreator = idCreator;
        EventName = eventName;
        Title = title;
        Description = description;
        StartDate = startDate;
        EndDate = endDate;
    
    }

}
}
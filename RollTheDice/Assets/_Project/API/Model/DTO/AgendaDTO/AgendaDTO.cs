using System;
using System.Collections.Generic;

namespace RollTheDice.API.Models.AgendaDTO
{
public class AgendaDTO
{
    public long Id { get; set; }
    public string Title { get; set; }
    public string Description { get; set; }

      public List<long> IdParticipants { get; set; } = new List<long>();

    public long IdOwners { get; set; } 

    public List<long> IdEvents { get; set; } = new List<long>();

    public AgendaDTO(long id, string title, string description, List<long> idParticipants, long idOwners, List<long> idEvents)
    {
        Id = id;
        Title = title;
        Description = description;
        IdParticipants = idParticipants;
        IdOwners = idOwners;
        IdEvents = idEvents;
        
    }

    public AgendaDTO(){}

}
}
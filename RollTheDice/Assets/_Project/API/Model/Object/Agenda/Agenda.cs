using System;
using System.Collections.Generic;
using RollTheDice.API.Models.Users;


namespace RollTheDice.API.Models.Agenda
{
public class Agendas
{
    public long Id { get; set; }
    public string Title { get; set; }
    public string Description { get; set; }

    public List<User> Participants { get; set; } = new List<User>();

    public User Owners { get; set; } = new User();

    public List<AgendaEvent> Events { get; set; } = new List<AgendaEvent>();

    public Agendas(){}
    public Agendas(int id, string title, string description, List<User> participants, List<AgendaEvent> events, User owners)
    {
        Id = id;
        Title = title;
        Description = description;
        Participants = participants;
        Events = events;
        Owners = owners;
    }


}
}
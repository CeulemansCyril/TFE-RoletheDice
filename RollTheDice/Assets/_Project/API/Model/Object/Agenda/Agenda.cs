using System;
using System.Collections.Generic;
using Assets._Project.API.Model.Object.User;

namespace Assets._Project.API.Model.Object.Agenda
{
public class Agendas
{
    public long Id { get; set; }
    public string Title { get; set; }
    public string Description { get; set; }

    public List<Users> Participants { get; set; } = new List<Users>();

    public Users Owners { get; set; } = new Users();

    public List<AgendaEvent> Events { get; set; } = new List<AgendaEvent>();

    public Agendas(){}
    public Agendas(int id, string title, string description, List<Users> participants, List<AgendaEvent> events, Users owners)
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
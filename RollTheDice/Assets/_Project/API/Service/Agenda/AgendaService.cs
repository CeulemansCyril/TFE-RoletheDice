using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.AgendaDTO;
using Assets._Project.API.Model.Object.Agenda;
using Assets._Project.API.Model.Object.User;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;



namespace RollTheDice.API.Service.Agenda
{
    public class AgendaService : ApiService
    {
        private CatchError onError;
        public AgendaService(string endpoint) : base("agenda")
        {
        }

       
        // ------------------- AGENDA -------------------------
        public Awaitable<AgendaDTO> CreateAgenda<AgendaDTO>(AgendaDTO agenda)
        {
            return CreateAsync("/CreateAgenda", agenda);
        }

        public Awaitable<AgendaDTO> UpdateAgenda<AgendaDTO>(AgendaDTO agenda)
        {
            return UpdateAsync("/UpdateAgenda", agenda);
        }

        public Awaitable<AgendaDTO> GetAgendaById<AgendaDTO>(long id)
        {
            return GetAsync<AgendaDTO>("/GetAgendaById/" + id);
        }

        public Awaitable<string> DeleteAgenda (AgendaDTO agenda)
        {
            return DeleteAsync("/DeleteAgendaById/" + agenda.Id);
        }

        public Agendas AgendaDTOToEntity(AgendaDTO dto)
        {
            Agendas agenda = new Agendas();
            agenda.Id = dto.Id;
            agenda.Title = dto.Title;
            agenda.Description = dto.Description;
            agenda.Events = new List<AgendaEvent>();
            agenda.Owners = new Users { Id = dto.IdOwners };
            agenda.Participants = new List<Users>();
            foreach (var participantId in dto.IdParticipants)
            {
                agenda.Participants.Add( new Users { Id = participantId } );
            }
            return agenda;
        }

        public AgendaDTO EntityToAgendaDTO(Agendas entity)
        {
            AgendaDTO dto = new AgendaDTO();
            dto.Id = entity.Id;
            dto.Title = entity.Title;
            dto.Description = entity.Description;
            dto.IdOwners = entity.Owners.Id;
            dto.IdParticipants = new List<long>();
            foreach (var participant in entity.Participants)
            {
                dto.IdParticipants.Add(participant.Id);
            }
            return dto;
        }


        // ------------------- EVENTS -------------------------

 
        public Awaitable<AgendaEventDTO> CreateAgendaEvent<AgendaEventDTO>(AgendaEventDTO agenda)
        {
            return CreateAsync("/CreateAgendaEvent", agenda);
        }

     
        public Awaitable<AgendaEventDTO> UpdateAgendaEvent<AgendaEventDTO>(AgendaEventDTO agenda)
        {
            return UpdateAsync("/UpdateAgendaEvent", agenda);
        }

        
        public Awaitable<AgendaEventDTO[]> GetAllAgendaEventsByAgendaId<AgendaEventDTO>(long id)
        {
            return GetAsync<AgendaEventDTO[]>("/GetAgendaById/" + id);
        }

        public Awaitable<string> DeleteAgendaEvent (AgendaEventDTO agenda)
        {
            return DeleteAsync("/DeleteAgendaById/" + agenda.EventId);
        }
        public AgendaEvent AgendaEventDTOToEntity(AgendaEventDTO dto)
        {
            AgendaEvent agendaEvent = new AgendaEvent();
            agendaEvent.EventId = dto.EventId;
            agendaEvent.Title = dto.Title;
            agendaEvent.Description = dto.Description;
            agendaEvent.StartDate = dto.StartDate;
            agendaEvent.EndDate = dto.EndDate;
            return agendaEvent;
        }

        public AgendaEventDTO EntityToAgendaEventDTO(AgendaEvent entity)
        {
            AgendaEventDTO dto = new AgendaEventDTO();
            dto.EventId = entity.EventId;
            dto.Title = entity.Title;
            dto.Description = entity.Description;
            dto.StartDate = entity.StartDate;
            dto.EndDate = entity.EndDate;
            return dto;
        }

    }

    
}
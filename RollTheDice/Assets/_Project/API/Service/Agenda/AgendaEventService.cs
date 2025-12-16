using System;
using System.Collections;
using RollTheDice.API.Interface;
using RollTheDice.API.Models.Agenda; 



namespace RollTheDice.API.Service.Agenda
{
    
    public class AgendaEventService : IApiService<AgendaEvent>
    {
          private string onError;
        public AgendaEventService(string endpoint) : base("agenda")
        {
        }

         public IEnumerator CreateAgendaEvent(AgendaEvent agenda)
        {
            AgendaEvent lastResponse = new AgendaEvent();
            return  Create("/CreateAgendaEvent",agenda, response => lastResponse = response, error => onError = error);
        }

        public IEnumerator UpdateAgendaEvent(AgendaEvent agendas)
        {
            string lastResponse ="";
            return Update("/UpdateAgendaEvent",agendas, response => lastResponse = response, error => onError = error);
        }

        public IEnumerator GetAgendaEventByAgendaId(long id)
        {
            AgendaEvent[] lastResponse = null;
            return GetAll("/GetAgendaById/"+id,response => lastResponse = response, error => onError = error) ;
        }

        public IEnumerator DeleteAgendaEvent(long id)
        {
            string lastResponse =""; 
            return Delete("/DeleteAgendaById/"+id,response => lastResponse = response, error => onError = error ); 
        }
    } 
}
using System;
using System.Collections;
using RollTheDice.API.Interface;
using RollTheDice.API.Models.Agenda; 


namespace RollTheDice.API.Service.Agenda
{
    public class AgendaService : IApiService<Agendas>
    {
        private string onError;
        public AgendaService(string endpoint) : base("agenda")
        {
        }

       

        public IEnumerator CreateAgenda(Agendas agenda)
        {
            Agendas lastResponse = new();
            return  Create("/CreateAgenda",agenda, response => lastResponse = response, error => onError = error);
        }

        public IEnumerator UpdateAgenda(Agendas agendas)
        {
            string lastResponse ="";
            return Update("/UpdateAgenda",agendas, response => lastResponse = response, error => onError = error);
        }

        public IEnumerator GetAgendaById(long id)
        {
            Agendas lastResponse = new Agendas();
            return GetById("/GetAgendaById/"+id,response => lastResponse = response, error => onError = error) ;
        }

        public IEnumerator DeleteAgenda(long id)
        {
            string lastResponse =""; 
            return Delete("/DeleteAgendaById/"+id,response => lastResponse = response, error => onError = error ); 
        }

       
        
    }
}
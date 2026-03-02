using System;
using System.Collections.Generic;
namespace Assets._Project.API.Model.DTO
{
    public class ConversationDTO{
        public long Id { get; set; }
        public List<long> IdParticipants { get; set; }
        public DateTime CreatedAt { get; set; }
        public List<long> IdMessages { get; set; }

        public ConversationDTO(){}
        public ConversationDTO(long id,List<long> idPaticipant,DateTime createdAt, List<long> idMessage)
        {
            Id = id;
            IdParticipants = idPaticipant;
            CreatedAt = createdAt;
            IdMessages = idMessage;
        }

  }
}
using System;
using System.Collections.Generic;
using RollTheDice.API.Models.Users;

namespace RollTheDice.API.Models
{
    public class Conversations
    {
         private long Id { get; set; }
         private List<UserIdentifantData> Participants { get; set; }
         private DateTime CreatedAt { get; set; }
        private List<Message> Messages { get; set; }

        public Conversations(){}
        public Conversations(long id, List<UserIdentifantData> participants, DateTime createdAt,List<Message> messages)
        {
            Id = id;
            Participants = participants;
            CreatedAt = createdAt;
            Messages = messages;
        }
    }
}
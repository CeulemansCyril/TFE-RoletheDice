using System;
using System.Collections.Generic;
using Assets._Project.API.Model.Object.User;

namespace Assets._Project.API.Model.Object
{
    public class Conversations
    {
         public long Id { get; set; }
        public List<UserIdentifantData> Participants { get; set; }
        public DateTime CreatedAt { get; set; }
        public List<Message> Messages { get; set; }

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
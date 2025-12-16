 using System;
using RollTheDice.API.Models.Users;

namespace RollTheDice.API.Models.Chat
{
    public class ChatMessage
    {
        public long Id { get; set; }
        public string Message { get; set; }
        public bool IsModified { get; set; }
        public DateTime SentAt  { get; set; }

        public UserIdentifantData Sender { get; set; }

        public ChatMessage(){}
        public ChatMessage(long id, String message, bool isModified, DateTime sentAt, UserIdentifantData sender)
        {
            Id = id;
            Message = message;
            Sender = sender;
            SentAt = sentAt;
            
        }
    }
}
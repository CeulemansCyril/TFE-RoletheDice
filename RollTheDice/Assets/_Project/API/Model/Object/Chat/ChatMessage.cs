using System;
using Assets._Project.API.Model.Object.User;

namespace Assets._Project.API.Model.Object.Chat
{
    public class ChatMessage
    {
        public long Id { get; set; }
        public string Message { get; set; }
        public bool IsModified { get; set; }
        public DateTime SentAt  { get; set; }

        public UserIdentifantData Sender { get; set; }

        public long idChatChanel { get; set; }

        public ChatMessage(){}
        public ChatMessage(long id, String message, bool isModified, DateTime sentAt, UserIdentifantData sender, long idChatChanel)
        {
            Id = id;
            Message = message;
            Sender = sender;
            SentAt = sentAt;
            this.idChatChanel = idChatChanel;
        }
    }
}
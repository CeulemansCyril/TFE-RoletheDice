using System;

namespace RollTheDice.API.Models.ChatDTO
{
    public class ChatMessageDTO
    {
        public long Id { get; set; }
        public long IdSender { get; set; }

        public string MessageContent { get; set; }

        public bool IsModified { get; set; }

        public DateTime SentAt { get; set; }
        public long IdChatChanel { get; set; }

        public ChatMessageDTO(){}
        public ChatMessageDTO(long id, long idSender, long idChatChanel, DateTime dateTime, bool isModified)
        {
            Id = id;
            IdSender = idSender;
            IdChatChanel = idChatChanel;
            SentAt = dateTime;
            IdChatChanel = idChatChanel;
            IsModified = isModified;
        
        }
    }
}
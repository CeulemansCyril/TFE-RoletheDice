using System;

namespace RollTheDice.API.DTO
{
    public class MessageDTO
    {
        public long Id { get; set; }
        public string Content { get; set; }
        public string Sender { get; set; }
        public DateTime SentAt { get; set; }
        public bool IsRead { get; set; }
        public bool IsModified { get; set; }
        public string FileURL { get; set; }
        public long IdConversation { get; set; }
        public MessageDTO() { }
        public MessageDTO(long id, string content, string sender, DateTime sentAt, bool isRead, bool isModified)
        {
            Id = id;
            Content = content;
            Sender = sender;
            SentAt = sentAt;
            IsRead = isRead;
            IsModified = isModified;

        }
    }
}
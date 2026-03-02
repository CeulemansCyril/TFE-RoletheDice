using System;
using Assets._Project.API.Enums;

namespace Assets._Project.API.Model.DTO
{
    public class NotificationDTO
    {
        public long Id { get; set; }
        public string Message { get; set; }
        public NotificationType Type { get; set; }
        public bool Read { get; set; }
        public long ReceiverId { get; set; }
        public DateTime Timestamp { get; set; }

        public NotificationDTO() { }
        public NotificationDTO(long id, string message, NotificationType type, bool read, long receiverId, DateTime timestamp)
        {
            Id = id;
            Message = message;
            Type = type;
            Read = read;
            ReceiverId = receiverId;
            Timestamp = timestamp;
        }

    }
}
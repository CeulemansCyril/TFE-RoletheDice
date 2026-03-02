using System;
using Assets._Project.API.Enums;

namespace Assets._Project.API.Model.Object
{
    public class Notification
    {
        private long Id { get; set; }
        private string Message { get; set; }
        private NotificationType Type { get; set; }
        private bool Read { get; set; }
        private long ReceiverId { get; set; }
        private DateTime Timestamp { get; set; }

        public Notification(){}
        public Notification(long id, string message, NotificationType type, bool read, long receiverId, DateTime timestamp)
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
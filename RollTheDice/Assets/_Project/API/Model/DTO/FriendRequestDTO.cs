using System;
using RollTheDice.API.Enums;

namespace RollTheDice.API.DTO
{
    public class FriendRequestDTO
    {
         private long Id { get; set; }
        private long IdSender { get; set; }
        private long IdReceiver { get; set; }
        private RequestStatus Status { get; set; }
        private DateTime SentTime { get; set; }

        public FriendRequestDTO(){}
        public FriendRequestDTO(long id, long idSender, long idReceiver, RequestStatus status, DateTime sentTime)
        {
            Id = id;
            IdSender = idSender;
            IdReceiver = idReceiver;
            Status = status;
            SentTime = sentTime;
        }
    }
}
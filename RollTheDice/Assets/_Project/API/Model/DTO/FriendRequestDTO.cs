using System;
using Assets._Project.API.Enums;
 

namespace Assets._Project.API.Model.DTO
{
    public class FriendRequestDTO
    {
         public long Id { get; set; }
        public long IdSender { get; set; }
        public long IdReceiver { get; set; }
        public RequestStatus Status { get; set; }
        public DateTime SentTime { get; set; }

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
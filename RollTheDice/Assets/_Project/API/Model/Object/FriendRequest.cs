using System;
using RollTheDice.API.Enums;
using RollTheDice.API.Models.Users;

namespace RollTheDice.API.Models
{
    public class FriendRequest
    {
        public long Id { get; set; }
        public UserIdentifantData Sender { get; set; }
        public UserIdentifantData Receiver { get; set; }
        public RequestStatus Status { get; set; }
        public DateTime SentTime { get; set; }

        public FriendRequest(){}
        public FriendRequest(long id, UserIdentifantData sender, UserIdentifantData receiver, RequestStatus status, DateTime sentTime)
        {
            Id = id;
            Sender = sender;
            Receiver = receiver;
            Status = status;
            SentTime = sentTime;
            
        }
    }
}
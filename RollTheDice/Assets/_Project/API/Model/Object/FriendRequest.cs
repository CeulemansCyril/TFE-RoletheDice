using System;
using Assets._Project.API.Enums;
using Assets._Project.API.Model.Object.User;


namespace Assets._Project.API.Model.Object
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
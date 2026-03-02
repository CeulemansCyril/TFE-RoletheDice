using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using UnityEngine;

namespace Assets._Project.API.Service
{
    public class FriendRequestService : ApiService
    {
        private CatchError onError;
        public FriendRequestService() : base("friend-requests") { }

        
        public Awaitable<string> CreateFriendRequest(FriendRequestDTO friendRequestDTO)
        {
            return UpdateAsync<string>("/create/"+friendRequestDTO.IdSender+"/"+friendRequestDTO.IdReceiver, "");
        } 

        public Awaitable<string> FriendRequesteAcceptted(long id)
        {
            return UpdateAsync<string>("/accept/" + id, "");
        }
         public Awaitable<string> FriendRequesteRejected(long id)
        {
            return UpdateAsync<string>("/reject/" + id, "");
        }

       
    }
}

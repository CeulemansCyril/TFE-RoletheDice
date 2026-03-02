using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.UserDTO;
using Assets._Project.API.Model.Object;
using Assets._Project.API.Model.Object.Agenda;
using Assets._Project.API.Model.Object.Game;
using Assets._Project.API.Model.Object.User;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using UnityEngine;
using UnityEngine.Networking;

namespace Assets._Project.API.Service.User
{
    public class UserService : ApiService
    {
        private CatchError onError;
        public UserService() : base("user") { }

        // ------------------- User                -------------------------
        public Awaitable<UserDTO> UpdateUser(UserDTO user)
        {
            return UpdateAsync<UserDTO>("/UpdateUser ", user);
        }
        
        public Awaitable<string> DeleteUser(long id)
        {
            return DeleteAsync("/DeleteUser/" + id);
        }

        public Awaitable<UserDTO> GetUserById(long id)
        {
            return GetAsync<UserDTO>("/GetUserByID/" + id);
        }

        public Awaitable<UserDTO> GetUserByUserName(string name)
        {
            return GetAsync<UserDTO>("/GetUserByUsername/" + name);
        }
        public Awaitable<UserDTO> GetUserByEmail(string email)
        {
            return GetAsync<UserDTO>("/GetUserByEmail/" + email);
        }

        public Awaitable<UserDTO[]> GetAllUsers( )
        {
            return GetAllAsync<UserDTO>("/GetAllUsers");
        }
        public Awaitable<UserDTO[]> GetAllUsersActive()
        {
            return GetAllAsync<UserDTO>("/GetAllUsersActive");
        }
        
        public Awaitable<UserDTO> AddFriend (long idUser, long idfriend)
        {
            return GetAsync<UserDTO>("/AddFriend/" + idUser + "/" + idfriend);
        }
        public Awaitable<UserDTO> BlockUser(long idUser, long blockUserID)
        {
            return GetAsync<UserDTO>("/BlockUser/" + idUser + "/" + blockUserID);
        }
        //TODO : faire une requete post avec un body qui contient la query et l'id de l'utilisateur pour eviter les probleme de longueur d'url
        /*  public Awaitable<UserDTO[]> SearchAvailableUsers(string query,long idUser )
          {
              return CreateManyAsync<UserDTO>("/SearchAvailableUsers/" + idUser ,query);
          }*/




        public Users UsersDTOToUsers(UserDTO usersDTO)
        {
            Users users = new Users();
            users.Id = usersDTO.Id;
            users.Username = usersDTO.Username;
            users.Email = usersDTO.Email;
            users.DisplayName = usersDTO.DisplayName;
            users.DateOfBirth = usersDTO.DateOfBirth;
            users.RoleUser = usersDTO.RoleUser;

            users.BlockedUsers = usersDTO.BlockedUsers;
            users.Friends = usersDTO.Friends;

            users.UserConversations = new List<Conversations>();
            foreach(var conversationId in usersDTO.IdConversations)
            {
                Conversations conversation = new Conversations();
                conversation.Id = conversationId;
                users.UserConversations.Add(conversation);
            }

            users.Players = new List<Players>();
            foreach (var playerId in usersDTO.IdPlayers)
            {
                Players player = new Players();
                player.Id = playerId;
                users.Players.Add(player);
            }

            users.AgendaEvent = new List<AgendaEvent>();
            foreach (var agendaId in usersDTO.IdAgendaEvent)
            {
                AgendaEvent agenda = new AgendaEvent();
                agenda.EventId = agendaId;
                users.AgendaEvent.Add(agenda);
            }

            users.UserCreationContent = new List<UserCreationContent>();
            foreach (var contentId in usersDTO.IdUserCreationContent)
            {
                UserCreationContent content = new UserCreationContent();
                content.Id = contentId;
                users.UserCreationContent.Add(content);
            }

            return users;
        }

        public UserDTO UsersToUsersDTO(Users users)
        {
            UserDTO usersDTO = new UserDTO();
            usersDTO.Id = users.Id;
            usersDTO.Username = users.Username;
            usersDTO.Email = users.Email;
            usersDTO.DisplayName = users.DisplayName;
            usersDTO.DateOfBirth = users.DateOfBirth;
            usersDTO.RoleUser = users.RoleUser;
            usersDTO.BlockedUsers = users.BlockedUsers;
            usersDTO.Friends = users.Friends;
            usersDTO.IdConversations = new List<long>();
            foreach (var conversation in users.UserConversations)
            {
                usersDTO.IdConversations.Add(conversation.Id);
            }
            usersDTO.IdPlayers = new List<long>();
            foreach (var player in users.Players)
            {
                usersDTO.IdPlayers.Add(player.Id);
            }
            usersDTO.IdAgendaEvent = new List<long>();
            foreach (var agenda in users.AgendaEvent)
            {
                usersDTO.IdAgendaEvent.Add(agenda.EventId);
            }
            usersDTO.IdUserCreationContent = new List<long>();
            foreach (var content in users.UserCreationContent)
            {
                usersDTO.IdUserCreationContent.Add(content.Id);
            }
            return usersDTO;
        }

        // ------------------- UserCreationContent -------------------------

        public Awaitable<string> DeleteUserCreationContent(long id)
        {
                        return DeleteAsync("/DeleteUserCreationContent/" + id);

        }
        public Awaitable<string> DeleteAllUserCreationContentByUser(long id)
        {
            return DeleteAsync("/DeleteAllUserCreationContentByUser/" + id);

        }
        
        public Awaitable<UserCreationContentDTO> GetUserCreationContentByID <UserCreationContentDTO>(long id)
        {
            return GetAsync<UserCreationContentDTO>("/GetUserCreationContentByID/" + id);
        }

        public Awaitable<UserCreationContentDTO[]> GetAllUserCreationContentByUser<UserCreationContentDTO>(long id)
        {
            return GetAllAsync<UserCreationContentDTO>("/GetAllUserCreationContentByUser/" + id);
        }


 

       

    }
}

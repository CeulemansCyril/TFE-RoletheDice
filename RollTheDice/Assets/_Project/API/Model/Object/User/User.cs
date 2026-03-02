

using System;
using System.Collections.Generic;
using Assets._Project.API.Enums;
using Assets._Project.API.Model.Object.Agenda;
using Assets._Project.API.Model.Object.Game;
using Assets._Project.API.Model.Object.User; 

namespace Assets._Project.API.Model.Object.User
{
    public class Users

    {
        public long Id { get; set; }
        public string Username { get; set; }
        public string Email { get; set; }
        public string DisplayName { get; set; }
        public DateTime DateOfBirth { get; set; }
        public RoleUser RoleUser { get; set; }
        public List<UserIdentifantData> BlockedUsers { get; set; }
        public List<UserIdentifantData> Friends { get; set; }
        public List<Conversations> UserConversations { get; set; }
        public List<AgendaEvent> AgendaEvent { get; set; }
        public List<Players> Players { get; set; }
        public List<UserCreationContent> UserCreationContent { get; set; }
        public Users() { }

        public Users(int id, string username, string email, DateTime dateOfBirth, RoleUser roleUser, List<UserIdentifantData> blockedUsers,
                    List<UserIdentifantData> friends, List<Conversations> conversations, List<AgendaEvent> agendaEvents, List<Players> players, List<UserCreationContent> userCreationContent
                    )
        {
            Id = id;
            Username = username;
            Email = email;
            DateOfBirth = dateOfBirth;
            RoleUser = roleUser;
            BlockedUsers = blockedUsers;
            Friends = friends;
            UserConversations = conversations;
            AgendaEvent = agendaEvents;
            Players = players;
            UserCreationContent = userCreationContent;
        }
    }

}

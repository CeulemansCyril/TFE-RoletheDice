using System;
using System.Collections.Generic;
using RollTheDice.API.Enums;


namespace RollTheDice.API.DTO.UserDTO
{
    public class UserDTO
    {
        public long Id { get; set; }
        public string Username { get; set; }
        public string Email { get; set; }
        public string DisplayName { get; set; }
        public DateTime DateOfBirth { get; set; }
        public RoleUser RoleUser { get; set; }
        public List<long> IdPlayers { get; set; }
        public List<long> IdGamesAsAdmin { get; set; }
        public List<long> IdGamesAsCreator { get; set; }
        public List<long> IdConversations { get; set; }
        public long IdUserCreationContent { get; set; }

        public UserDTO(){}
        public UserDTO(long id, string username, string email, string displayName , DateTime dateOfBirth, RoleUser roleUser, List<long> idPlayers,
            List<long> idGamesAsAdmin, List<long> idGamesAsCreator, List<long> idConversations, long idUserCreationContent
         )
        {
            Id = id;
            Username = username;
            Email = email;
            DisplayName = displayName;
            DateOfBirth = dateOfBirth;
            RoleUser = roleUser;
            IdPlayers = idPlayers;
            IdGamesAsAdmin = idGamesAsAdmin;
            IdGamesAsCreator = idGamesAsCreator;
            IdConversations = idConversations;
            IdUserCreationContent = idUserCreationContent;
            
        }
    }
}
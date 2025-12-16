

using System;
using System.Collections.Generic;
using RollTheDice.API.Enums;
using RollTheDice.API.Models.Agenda;
using RollTheDice.API.Models.Game;

namespace RollTheDice.API.Models.Users{
    public class User
{
    public int Id { get; set; }
    public string Username { get; set; }
    public string Email { get; set; }
    public string DisplayName { get; set; }
    public DateTime DateOfBirth { get; set; }
    public RoleUser RoleUser { get; set; }
    public List<UserIdentifantData> BlockedUsers { get; set; }
    public List<UserIdentifantData> Friends { get; set; }
    public List<Conversations> UserConversations  { get; set; }
    public List<Agendas> Agendas { get; set; }
    public List<Players> Players { get; set; }
    public long IdUserCreationContent { get; set; }
    public User() { }

    public User(int id, string username, string email, DateTime dateOfBirth, RoleUser roleUser,List<UserIdentifantData> blockedUsers,
                List<UserIdentifantData> friends,List<Conversations> conversations,List<Agendas> agendas,List<Players> players, long idUserCreationContent
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
        Agendas = agendas;
        Players = players;
        IdUserCreationContent = idUserCreationContent;

    }
}
}

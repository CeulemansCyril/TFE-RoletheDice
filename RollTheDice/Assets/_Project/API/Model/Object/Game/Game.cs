using System.Collections.Generic;
using RollTheDice.API.Models.Chat;
using RollTheDice.API.Models.Game.Books;
using RollTheDice.API.Models.Game.Map;
using RollTheDice.API.Models.Users;

namespace RollTheDice.API.Models.Game
{
    public class Games
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public User Creator { get; set; }
        public List<Players> Players { get; set; }
        public List<User> PlayAdmins { get; set; }
        public List<ChatChanel> ChatChanels { get; set; }
        public Maps ActiveMap { get; set; }
        private List<Book> Books { get; set; }

        public Games(){}
        public Games(long id, string name, User creator, List<Players> players, List<User> playersAdmins,List<ChatChanel> chatChanels, Maps maps, List<Book> books)
        {
            Id = id;
            Name = name;
            Creator = creator;
            Players = players;
            PlayAdmins = playersAdmins;
            ChatChanels = chatChanels;
            ActiveMap = maps;
            Books = books;
        }
    }
}
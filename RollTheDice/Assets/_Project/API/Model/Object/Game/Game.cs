using System.Collections.Generic;
using Assets._Project.API.Model.Object.Chat;
using Assets._Project.API.Model.Object.Game.Book;
using Assets._Project.API.Model.Object.Game.Map;
using Assets._Project.API.Model.Object.User;

namespace Assets._Project.API.Model.Object.Game
{
    public class Games
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public Users Creator { get; set; }
        public List<Players> Players { get; set; }
        public List<Users> PlayAdmins { get; set; }
        public List<ChatChanel> ChatChanels { get; set; }
        public Maps ActiveMap { get; set; }
        public List<Books> Books { get; set; }

        public Games(){}
        public Games(long id, string name, Users creator, List<Players> players, List<Users> playersAdmins,List<ChatChanel> chatChanels, Maps maps, List<Books> books)
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
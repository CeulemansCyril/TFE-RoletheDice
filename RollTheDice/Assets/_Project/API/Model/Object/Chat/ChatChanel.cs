using System.Collections.Generic;

namespace Assets._Project.API.Model.Object.Chat
{
    public class ChatChanel
    {
        public long Id { get; set; }
        public string Name { get; set; }

        public List<ChatMessage> ChatMessages{ get; set; } = new List<ChatMessage>();

        public long IdGame { get; set; }

        public ChatChanel(){}
        public ChatChanel(long idGame, string name, long id, List<ChatMessage> chatMessage)
        {
            IdGame = idGame;
            Name = name;
            Id = id;
            ChatMessages = chatMessage;
        }

    }
}
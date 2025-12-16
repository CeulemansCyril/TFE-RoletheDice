using System.Collections.Generic;

namespace RollTheDice.API.Models.ChatDTO
{
    public class ChatChanelDTO
    {
        private long Id { get; set; }

        private string Name { get; set; }

        private List<long> IdMessages { get; set; }

        private long IdGame { get; set; }

        public ChatChanelDTO(){}
        public ChatChanelDTO(long id,string name, List<long> idMessage,long idGame)
        {
            Id = id;
            Name = name;
            IdMessages = idMessage;
            IdGame = idGame;
        }
    }
}
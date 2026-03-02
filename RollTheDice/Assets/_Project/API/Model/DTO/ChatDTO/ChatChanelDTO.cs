using System.Collections.Generic;

namespace Assets._Project.API.Model.DTO.ChatDTO
{
    public class ChatChanelDTO
    {
        public long Id { get; set; }

        public string Name { get; set; }

        public List<long> IdMessages { get; set; }

        public long IdGame { get; set; }

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
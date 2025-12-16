using System.Collections.Generic;

namespace RollTheDice.API.DTO.GameDTO
{
    public class PlayersDTO
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public long IdLoreBook { get; set; }
        public List<long> IdStorages { get; set; }
        public long IdCharacterModel { get; set; }
        public long IdUser { get; set; }
        public List<long> IdGame { get; set; }

        public PlayersDTO(){}
        public PlayersDTO(long id, string name, long idLoreBook, List<long> idStorages, long idCharacterModel, long idUser, List<long> idGame)
        {
            Id = id;
            Name = name;
            IdLoreBook = idLoreBook;
            IdStorages = idStorages;
            IdCharacterModel = idCharacterModel;
            IdUser = idUser;
            IdGame = idGame;
        }

    }
}
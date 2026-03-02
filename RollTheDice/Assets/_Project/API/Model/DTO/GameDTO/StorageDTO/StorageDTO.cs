using System.Collections.Generic;

namespace Assets._Project.API.Model.DTO.GameDTO.StorageDTO
{
    public class StorageDTO
    {
        public long Id { get; set;}
        public string Name { get; set;}
        public string Description { get; set;}
        public List<long> IdItems { get; set;} = new List<long>();
        public long IdGame { get; set;}
        public long IdPlayer { get; set;}

        public StorageDTO(){}
        public StorageDTO(long id, string name, string description, List<long> idItems,long idGame,long idPlayer){
            Id = id;
            Name = name;
            Description = description;
            IdItems = idItems;
            IdGame = idGame;
            IdPlayer = idPlayer;
        }
    }
}
using System.Collections.Generic;
namespace Assets._Project.API.Model.DTO.GameDTO
{
    public class GameDTO{
        public long Id { get; set; }
        public string Name { get; set; }
        public long IdCreator { get; set; }
        public List<long> IdPlayers { get; set; }
        public List<long> IdPlayAdmins { get; set; }
        public List<long> IdChatChanels { get; set; }
        public long IdGameBundle { get; set; }
        public long IdActiveMap { get; set; }
        public List<long> IdBooks { get; set; }

        public GameDTO(){}
        public GameDTO(long id, string name, long idCreator, List<long> idPlayers, 
        List<long> idPlayAdmins,List<long> idChatChanels,long idGameBundle,long idActiveMap, List<long> idBooks)
        {
            Id = id;
            Name = name;
            IdCreator = idCreator;
            IdPlayers = idPlayers;
            IdPlayAdmins = idPlayAdmins;
            IdChatChanels = idChatChanels;
            IdGameBundle = idGameBundle;
            IdActiveMap = idActiveMap;
            IdBooks = idBooks;
        }
    }
}
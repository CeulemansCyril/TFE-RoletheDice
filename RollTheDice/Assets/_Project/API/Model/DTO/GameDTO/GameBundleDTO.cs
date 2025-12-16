using System.Collections.Generic;

namespace RollTheDice.API.DTO.GameDTO
{
    public class GameBundleDTO
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public long IdCreator { get; set; }
        public List<long> IdGame { get; set; }
        public List<long> IdTokens { get; set; }
        public List<long> IdBooks { get; set; }
        public List<long> IdCurrencies { get; set; }
        public List<long> IdLootTables { get; set; }
        public List<long> IdMap { get; set; }

        public GameBundleDTO(){}
        public GameBundleDTO(long id, string name, long idCreator, List<long> idGame, List<long> idTokens,List<long> idBooks,List<long> idCurrencies,List<long> idLootTable,List<long> idMaps)
        {
            Id = id;
            Name = name;
            IdCreator = idCreator;
            IdGame = idGame;
            IdTokens = idTokens;
            IdBooks = idBooks;
            IdCurrencies = idCurrencies;
            IdLootTables = idLootTable;
            IdMap = idMaps;
        }
    }
}
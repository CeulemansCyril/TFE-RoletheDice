using System.Collections.Generic;
using RollTheDice.API.Models.Game.Books;
using RollTheDice.API.Models.Game.LootTable;
using RollTheDice.API.Models.Game.Map;
using RollTheDice.API.Models.Game.Money;
using RollTheDice.API.Models.Game.Token;
using RollTheDice.API.Models.Users;

namespace RollTheDice.API.Models.Game
{
    public class GameBundle
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public User Creator { get; set; }
        public List<Tokens> Tokens { get; set; }
        public List<Book> Books { get; set; }
        public List<Currency> Currencies { get; set; }
        public List<LootTables> LootTables { get; set; }
        public List<Maps> Maps { get; set; }

        public GameBundle(){}
        public GameBundle(long id, string name, User creator, List<Tokens> tokens, List<Book> books,List<Currency> currencies,List<LootTables> lootTables,List<Maps> maps)
        {
            Id = id;
            Name = name;
            Creator = creator;
            Tokens = tokens;
            Books = books;
            Currencies = currencies;
            LootTables = lootTables;
            Maps = maps;
        }

    }
}
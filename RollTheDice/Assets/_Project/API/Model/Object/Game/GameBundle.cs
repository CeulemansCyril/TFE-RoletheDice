using System.Collections.Generic;
using Assets._Project.API.Model.Object.Game.Book;
using Assets._Project.API.Model.Object.Game.LootTable;
using Assets._Project.API.Model.Object.Game.Map;
using Assets._Project.API.Model.Object.Game.Money;
using Assets._Project.API.Model.Object.Game.Token;
using Assets._Project.API.Model.Object.User;

namespace Assets._Project.API.Model.Object.Game
{
    public class GameBundle
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public Users Creator { get; set; }
        public List<Tokens> Tokens { get; set; }
        public List<Books> Books { get; set; }
        public List<Currency> Currencies { get; set; }
        public List<LootTables> LootTables { get; set; }
        public List<Maps> Maps { get; set; }

        public GameBundle(){}
        public GameBundle(long id, string name, Users creator, List<Tokens> tokens, List<Books> books,List<Currency> currencies,List<LootTables> lootTables,List<Maps> maps)
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
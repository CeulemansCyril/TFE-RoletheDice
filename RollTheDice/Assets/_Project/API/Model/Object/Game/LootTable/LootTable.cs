using System.Collections.Generic;



namespace RollTheDice.API.Models.Game.LootTable
{
    public class LootTables
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public List<LootElement> LootElements { get; set; } = new List<LootElement>();

        public LootTables(){}

        public LootTables(long id, string name, List<LootElement> lootElements)
        {
            Id = id;
            Name = name;
            LootElements = lootElements;
        }


    }
}
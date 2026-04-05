using Assets._Project.API.Model.DTO.GameDTO.LootTableDTO;
using System.Collections.Generic;



namespace Assets._Project.API.Model.Object.Game.LootTable
{
    public class LootTables
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public  LootElementDTO[] LootElements { get; set; } = new LootElementDTO[];
        public long IdGameBundle { get; set; }
        public LootTables(){}

        public LootTables(long id, string name, LootElementDTO[] lootElements, long idGameBundle)
        {
            Id = id;
            Name = name;
            LootElements = lootElements;
            IdGameBundle = idGameBundle;
        }


    }
}
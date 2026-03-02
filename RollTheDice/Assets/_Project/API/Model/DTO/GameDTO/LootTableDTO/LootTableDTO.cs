using System.Collections.Generic;

namespace Assets._Project.API.Model.DTO.GameDTO.LootTableDTO
{
     public class LootTableDTO
    {
        
        public long Id { get; set; }
        public string Name { get; set; }
        public List<LootElementDTO> LootElements { get; set; }
        public long IdGameBundle { get; set; }

        public LootTableDTO(){}
        public LootTableDTO(long id, string name, long idGameBundle, List<LootElementDTO> lootElements)
        {
            Id = id;
            Name = name;
            IdGameBundle = idGameBundle;
            LootElements = lootElements;
        }
    }
}
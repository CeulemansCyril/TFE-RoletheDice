using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.GameDTO.LootTableDTO;
using Assets._Project.API.Model.Object.Game.LootTable;
using System.Collections;
using System.Linq;
using UnityEngine;

namespace Assets._Project.API.Service.Game.LootTable
{
    public class LootTableService : ApiService
    {
        private CatchError onError;
        public LootTableService(string endpoint) : base("lootTable")
        {
        }

        public Awaitable<LootTableDTO> CreateLootTable(LootTableDTO lootTable, long idUser)
        {
                        return CreateAsync("/CreateLootTables/" + idUser, lootTable);
        }
        
        public Awaitable<LootTableDTO> UpdateLootTable(LootTableDTO lootTable)
        {
            return UpdateAsync("/UpdateLootTables ", lootTable);
        }

        public Awaitable<LootTableDTO> GetLootTableById(long id)
        {
            return GetAsync<LootTableDTO>("/GetLootTableById/" + id);
        }
          public Awaitable<LootTableDTO[]> GetAllLootTableByGameBundleId (long id)
        {
            return GetAllAsync<LootTableDTO>("/GetLootTableByByGameBundleId/"+id) ;
        }

       public Awaitable<string>DeleteLootTable(long id)
        {
            return DeleteAsync("/DeleteLootTables/" + id);
        }

    

        public LootTables LootTableDTOToLootTable(LootTableDTO lootTableDTO)
        {
            LootTables lootTable = new LootTables();
            lootTable.Id = lootTableDTO.Id;
            lootTable.Name = lootTableDTO.Name;
            lootTable.IdGameBundle = lootTableDTO.IdGameBundle;
            lootTable.LootElements = lootTableDTO.LootElements ;
            return lootTable;
        }

        public LootTableDTO LootTableToLootTableDTO(LootTables lootTable)
        {
            LootTableDTO lootTableDTO = new LootTableDTO();
            lootTableDTO.Id = lootTable.Id;
            lootTableDTO.Name = lootTable.Name;
            lootTableDTO.IdGameBundle = lootTable.IdGameBundle;
            lootTableDTO.LootElements = lootTable.LootElements  ;
            return lootTableDTO;
        }
    }
}
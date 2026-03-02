using UnityEngine.UIElements.Experimental;
using Assets._Project.API.Model.Object.Game.Money;
using Assets._Project.API.Enums;

namespace Assets._Project.API.Model.DTO.GameDTO.LootTableDTO
{
    public class LootElementDTO
    {
          public LootType Type { get; set; }
        public int MinAmount { get; set; }
        public int MaxAmount { get; set; }
        public int Weight { get; set; }
        public double DropChance { get; set; }

        public long IdDropObject { get; set; }
        public long IdDropMoney { get; set; }

        public Value Value{ get; set; }

        public LootElementDTO(){}
        public LootElementDTO(LootType type, int minAmount, int maxAmount, int weight, double dropChance, long idDropObject, long idDropMoney, Value value)
        {
            Type = type;
            MinAmount = minAmount;
            MaxAmount = maxAmount;
            Weight = weight;
            DropChance = dropChance;
            IdDropObject = idDropObject;
            IdDropMoney = idDropMoney;
            Value = value;
        }

    }
}
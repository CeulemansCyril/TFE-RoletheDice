using RollTheDice.API.Enums;
using UnityEditor;
using UnityEngine.UIElements.Experimental;
using RollTheDice.API.Models.Game.Money;
 

namespace RollTheDice.API.Models.Game.LootTable
{
    public class LootElement
    {
        public LootType Type { get; set; }
        public int MinAmount { get; set; }
        public int MaxAmount { get; set; }
        public int Weight { get; set; }
        public double DropChance { get; set; }
        public long IdDropObject { get; set; }
        public Value Value { get; set; }

        public LootElement(){}
        public LootElement(LootType type, int minAmount, int maxAmount,int weight,double dropChance, long idDropObject, Value value)
        {
            Type = type;
            MinAmount = minAmount;
            MaxAmount = maxAmount;
            Weight = weight;
            DropChance = dropChance;
            IdDropObject = idDropObject;
            Value = value;
        }
    }
}
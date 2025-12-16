using System.Collections.Generic;
using RollTheDice.API.Models.Game.Money;

namespace RollTheDice.API.Models.Game.Template
{
    public class CustomObject
    {
        private long Id { get; set;}
        private string Name { get; set;}
        private Dictionary<string,string> FieldValues { get; set;}
         private bool CanBeInInventory { get; set;}
         private Value Price { get; set;}

         public CustomObject(){}
         public CustomObject(long id, string name, Dictionary<string,string> fieldValues, bool canBeInInventory, Value price)
        {
            Id = id;
            Name = name;
            FieldValues = fieldValues;
            CanBeInInventory = canBeInInventory;
            Price = price;
        
        }

      
    }
}
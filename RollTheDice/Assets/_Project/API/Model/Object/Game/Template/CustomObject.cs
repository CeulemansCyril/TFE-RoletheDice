using System.Collections.Generic;
using Assets._Project.API.Model.Object.Game.Money;

namespace Assets._Project.API.Model.Object.Game.Templates
{
    public class CustomObject
    {
        public long Id { get; set;}
        public string Name { get; set;}
        public Dictionary<string,string> FieldValues { get; set;}
        public bool CanBeInInventory { get; set;}
        public Value Price { get; set;}

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
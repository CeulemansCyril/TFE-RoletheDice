
using System.Collections.Generic;
using Assets._Project.API.Model.Object.Game.Money;

namespace Assets._Project.API.Model.DTO.GameDTO.TemplateDTO
{
    public class CustomObjectDTO
    {
        public long Id { get; set;}
        public string Name { get; set;}
        public long IdTemplate { get; set;}
        public long IdGameBundles { get; set;}
        public Dictionary<string,string> FieldValues { get; set;}
        public bool CanBeInInventory { get; set;}
        public Value Price { get; set;}

        public CustomObjectDTO(){}
        public CustomObjectDTO(long id, string name, long idTemplate, long idGameBundles, Dictionary<string, string> fieldValues, bool canBeInInventory, Value price)
        {
            Id = id;
            Name = name;
            IdTemplate = idTemplate;
            IdGameBundles = idGameBundles;
            FieldValues = fieldValues;
            CanBeInInventory = canBeInInventory;
            Price = price;
        }

    }
}
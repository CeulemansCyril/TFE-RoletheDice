using System.Collections.Generic;
using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;
using Assets._Project.API.Model.Object.Game.Money;

namespace Assets._Project.API.Model.Object.Game.Templates
{
    public class CustomObject
    {
        public long Id { get; set;}
        public string Name { get; set;}
        public CustomObjectAttributeDTO[] Attributes { get; set;}
        public bool CanBeInInventory { get; set;}
        public Value Price { get; set;}

        public Template Template { get; set;}

        public CustomObject(){}
         public CustomObject(long id, string name, CustomObjectAttributeDTO[] attributes, bool canBeInInventory, Value price,Template template)
        {
            Id = id;
            Name = name;
            Attributes = attributes;
            CanBeInInventory = canBeInInventory;
            Price = price;
            Template = template;
        
        }

      
    }
}
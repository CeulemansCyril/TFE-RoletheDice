
using System.Collections.Generic;
using Assets._Project.API.Model.DTO.GameDTO.MoneyDTO;
using Assets._Project.API.Model.Object.Game.Money;

namespace Assets._Project.API.Model.DTO.GameDTO.TemplateDTO
{
    public class CustomObjectDTO
    {
        public long Id { get; set;}
        public string Name { get; set;}
        public long IdTemplate { get; set;}
        public long IdGameBundles { get; set;}
        public CustomObjectAttributeDTO[] CustomObjectAttributeDTO { get; set;}
        public bool CanBeInInventory { get; set;}
        public ValueDTO Price { get; set;}

        public CustomObjectDTO(){}
        public CustomObjectDTO(long id, string name, long idTemplate, long idGameBundles, CustomObjectAttributeDTO[] customObjectAttributeDTO, bool canBeInInventory, ValueDTO price)
        {
            Id = id;
            Name = name;
            IdTemplate = idTemplate;
            IdGameBundles = idGameBundles;
            CustomObjectAttributeDTO = customObjectAttributeDTO;
            CanBeInInventory = canBeInInventory;
            Price = price;
        }

    }
}
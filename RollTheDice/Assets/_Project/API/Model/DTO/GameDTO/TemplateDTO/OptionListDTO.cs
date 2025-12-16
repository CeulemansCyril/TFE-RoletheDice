using System.Collections.Generic;

namespace RollTheDice.API.DTO.GameDTO.TemplateDTO
{
    public class OptionListDTO
    {
        public long Id { get; set;}
        public string Name { get; set;}
        public List<string> Options { get; set;} = new List<string>();
        public long IdTemplateField { get; set;}

        public OptionListDTO(){}
        public OptionListDTO(long id, string name, List<string>options,long idTemplateField)
        {
            Id = id;
            Name = name;
            Options = options;
            IdTemplateField = idTemplateField;
        }
    }
}
using System.Collections.Generic;

namespace RollTheDice.API.DTO.GameDTO.TemplateDTO
{
    public class TemplateFieldDTO
    {
        public long Id { get; set;}
        public string Label { get; set;}
        public string Type { get; set;}
        public bool Required { get; set;}
        public double MinValue { get; set;}
        public double MaxValue { get; set;}
        public double PositionX { get; set;}
        public double PositionY { get; set;}
        public long IdOptionList { get; set;}
        public List<long> IdTemplates { get; set;}

        public TemplateFieldDTO(){}
        public TemplateFieldDTO(long id, string label, string type, bool required, double minValue, double maxValue, double positionX, double positionY, long idOptionList, List<long> idTemplates)
        {
            Id = id;
            Label = label;
            Type = type;
            Required = required;
            MinValue = minValue;
            MaxValue = maxValue;
            PositionX = positionX;
            PositionY = positionY;
            IdOptionList = idOptionList;
            IdTemplates = idTemplates;
        
        }
    }
}
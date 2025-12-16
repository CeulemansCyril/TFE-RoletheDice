using System.Collections.Generic;

namespace RollTheDice.API.Models.Game.Template
{
    public class Template
    {
        public long Id { get; set;}
        public string Name { get; set;}
        public List<TemplateField> TemplateFieldList { get; set;}

        public Template(){}
        public Template(long id, string name, List<TemplateField> templateFieldList)
        {
            Id = id;
            Name = name;
            TemplateFieldList = templateFieldList;
        }
    }
}
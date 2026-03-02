using System.Collections.Generic;

namespace Assets._Project.API.Model.DTO.GameDTO.TemplateDTO
{
    public class TemplateDTO
    {
        public long Id { get; set;}
        public string Name { get; set;}
        public long IdGameBundle { get; set;}
        public List<long> IdTemplateFieldList { get; set;}

        public TemplateDTO(){}
        public TemplateDTO(long id, string name, long idGameBundle, List<long>idTemplateFieldList )
        {
            Id = id;
            Name = name;
            IdGameBundle = idGameBundle;
            IdTemplateFieldList = idTemplateFieldList;
     
        }

    }
}
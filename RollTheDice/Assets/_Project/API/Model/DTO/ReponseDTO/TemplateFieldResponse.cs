using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;

namespace Assets._Project.API.Model.DTO.ReponseDTO
{
    public class TemplateFieldResponse 
    {
        public TemplateFieldDTO TemplateField { get; set; }
        public OptionListDTO OptionList { get; set; }

        public TemplateFieldResponse (){}
        public TemplateFieldResponse (TemplateFieldDTO templateFieldDTO, OptionListDTO optionList)
        {
            TemplateField = templateFieldDTO;
            OptionList = optionList;
        }
    }
}
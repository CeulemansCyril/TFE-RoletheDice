using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;

namespace Assets._Project.API.Model.DTO.ReponseDTO
{
    public class TemplateFieldResponse 
    {
        private OptionListDTO optionList;

        public TemplateFieldDTO TemplateField { get; set; }
        public OptionListDTO? OptionList { get => optionList; set => optionList = value; }

        public TemplateFieldResponse (){}
        public TemplateFieldResponse (TemplateFieldDTO templateFieldDTO, OptionListDTO optionList)
        {
            TemplateField = templateFieldDTO;
            OptionList = optionList;
        }
    }
}
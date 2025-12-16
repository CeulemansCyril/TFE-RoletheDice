using RollTheDice.API.DTO.GameDTO.TemplateDTO;

namespace RollTheDice.API.DTO.ReponseDTO
{
    public class TemplateFieldResponseDTO
    {
        public TemplateFieldDTO TemplateField { get; set; }
        public OptionListDTO OptionList { get; set; }

        public TemplateFieldResponseDTO(){}
        public TemplateFieldResponseDTO(TemplateFieldDTO templateFieldDTO, OptionListDTO optionList)
        {
            TemplateField = templateFieldDTO;
            OptionList = optionList;
        }
    }
}
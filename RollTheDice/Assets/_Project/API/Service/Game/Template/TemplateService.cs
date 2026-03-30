using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO.GameDTO.MoneyDTO;
using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;
using Assets._Project.API.Model.DTO.ReponseDTO;
using Assets._Project.API.Model.Object.Game.Money;
using Assets._Project.API.Model.Object.Game.Templates;
using System.Collections.Generic;
using System.Threading.Tasks;
using UnityEngine;

namespace Assets._Project.API.Service.Game.Templates
{
    class TemplateService : ApiService
    {

        public TemplateService() : base("template") { }

        // ------------------- CustomObject  -------------------------
        public Awaitable<CustomObjectDTO> CreateCustomObject(CustomObjectDTO customObject, long idUser)
        {
            return CreateAsync("/createCustomObject/" + idUser, customObject);
        }

        public Awaitable<CustomObjectDTO> UpdateCustomObject(CustomObjectDTO customObject)
        {
            return UpdateAsync("/updateCustomObject", customObject);
        }

        public Awaitable<string> DeleteCustomObject(long id)
        {
            return DeleteAsync("/deleteCustomObject/" + id);
        }

        public Awaitable<CustomObjectDTO> GetCustomObjectById(long id)
        {
            return GetAsync<CustomObjectDTO>("/getCustomObjectById/" + id);
        }

        public Awaitable<CustomObjectDTO[]> GetAllCustomObjectByGameBundleId(long id)
        {
            return GetAllAsync<CustomObjectDTO>("/GetAllCustomObjectByGameBundleId/" + id);
        }

        


        public   async Task<CustomObject> CustomObjectDTOToCustomObjectAsync(CustomObjectDTO dto)
        {
            CustomObject customObject = new CustomObject();
            customObject.Id = dto.Id;
            customObject.Name = dto.Name;
            customObject.Attributes = dto.CustomObjectAttributeDTO;
            customObject.CanBeInInventory = dto.CanBeInInventory;

            if (dto.Price != null)
            {
       
                var currency = dto.Price.CurrencyId.HasValue
                               ? new Currency { Id = dto.Price.CurrencyId.Value }
                               : new Currency();

                customObject.Price = new Value(dto.Price.Amount, currency);
            }
            TemplateDTO templateDTO = await GetTemplateById(dto.IdTemplate);
            customObject.Template = await TemplateDTOToTemplate(templateDTO);
            return customObject;
        }

        public CustomObjectDTO CustomObjectToCustomObjectDTO(CustomObject customObject, long idTemplate, long idGamebundle)
        {
            CustomObjectDTO dto = new CustomObjectDTO();
            dto.Id = customObject.Id;
            dto.Name = customObject.Name;
            dto.CustomObjectAttributeDTO = customObject.Attributes;
          
            dto.CanBeInInventory = customObject.CanBeInInventory;
            dto.Price = ValueToValueDTO( customObject.Price);
 
            dto.IdTemplate = idTemplate;
            dto.IdGameBundles = idGamebundle;
            return dto;

        }

        public Value ValueToDTO(ValueDTO valueDTO)
        {
            Value value = new Value();
            value.Amount = valueDTO.Amount;
            if(valueDTO.CurrencyId != null) value.Currency = new Currency { Id = (long)valueDTO.CurrencyId };
            else value.Currency = new Currency();
            return value;
        }
         public ValueDTO ValueToValueDTO(Value value)
        {
            ValueDTO dto = new ValueDTO();
            dto.Amount = value.Amount;
            dto.CurrencyId = value.Currency.Id;
            Debug.Log("ValueToValueDTO: Amount = " + dto.Amount + ", CurrencyId = " + dto.CurrencyId);
            return dto;
        }


        //-------------------- CustomObjectAttribu -------------------
        public Awaitable<CustomObjectAttributeDTO[]> CreateManyCustomObjectAttribute( CustomObjectAttributeDTO[] list)
        {
            return CreateManyAsync("/createManyCustomObjectAttribute", list);
        }

        public Awaitable<CustomObjectAttributeDTO[]> UpdateManyCustomObjectAttribute(CustomObjectAttributeDTO[] list)
        {
            return UpadateManyAsync("/updateManyCustomObjectAttribute", list);
        }

        public Awaitable<string> DeleteManyCustomObjectAttribute(long[] id)
        {
            return DeleteManyAsync("/deleteManyCustomObjectAttribute", id);
        }

        public Awaitable<CustomObjectAttributeDTO[]> GetCustomObjectAttributeByCustomObjectId(long id)
        {
            return GetAllAsync<CustomObjectAttributeDTO>("/GetAllAttributesByCustomObjectId/" + id);
        }


        // ------------------- OptionList    -------------------------

        public Awaitable<OptionListDTO> CreateOptionList(OptionListDTO customObject, long idUser)
        {
            return CreateAsync("/createOptionList/" + idUser, customObject);
        }

        public Awaitable<OptionListDTO[]> CreateManyOption(long id, OptionListDTO[] list)
        {
            return CreateManyAsync("/getOptionListByTemplateFieldId/" + id, list);
        }

        public Awaitable<OptionListDTO> UpdateOptionList(OptionListDTO customObject)
        {
            return UpdateAsync("/updateOptionList", customObject);
        }

        public Awaitable<OptionListDTO[]> UpdateManyOption(OptionListDTO[] list)
        {
            return UpadateManyAsync("/updateManyOptionList", list);
        }

        public Awaitable<string> DeleteOptionList(long id)
        {
            return DeleteAsync("/deleteOptionList/" + id);
        }

        public Awaitable<string> DeleteManyOptionList(long[] id)
        {
            return DeleteManyAsync("/deleteManyOptionList", id);
        }



        public Awaitable<OptionListDTO> GetOptionListById(long id)
        {
            return GetAsync<OptionListDTO>("/getOptionListById/" + id);
        }

        public Awaitable<OptionListDTO[]> GetOptionListByIdGameBundle(long id)
        {
            return GetAllAsync<OptionListDTO>("/GetAllOptionListByGameBundleId/" + id);
        }



        public OptionList OptionListDTOToOptionList(OptionListDTO dto)
        {
            OptionList optionList = new OptionList();
            optionList.Id = dto.Id;
            optionList.Name = dto.Name;
            optionList.Options = dto.Options;
 
            return optionList;
        }

        public OptionListDTO OptionListToOptionListDTO(OptionList optionList, long IdTemplateField, long idGameBundle)
        {
            OptionListDTO dto = new OptionListDTO();
            dto.Id = optionList.Id;
            dto.Name = optionList.Name;
            if(optionList.Options != null)  dto.Options = optionList.Options;
            dto.IdTemplateField = IdTemplateField;
            dto.IdGameBundle = idGameBundle;
            return dto;
        }

        // ------------------- Template      -------------------------
        public Awaitable<TemplateDTO> CreateTemplate(TemplateDTO customObject, long idUser)
        {
            return CreateAsync("/createTemplate/" + idUser, customObject);
        }

        public Awaitable<TemplateDTO> UpdateTemplate(TemplateDTO customObject)
        {
            return UpdateAsync("/updateTemplate", customObject);
        }

        public Awaitable<string> DeleteTemplate(long id)
        {
            return DeleteAsync("/deleteTemplate/" + id);
        }


        public Awaitable<TemplateDTO> GetTemplateById(long id)
        {
            return GetAsync<TemplateDTO>("/getTemplateById/" + id);
        }

        public Awaitable<TemplateDTO[]> GetAllTemplateByGameBundleId(long id)
        {
            return GetAllAsync<TemplateDTO>("/GetAllTemplateByGameBundleId/" + id);
        }



        public async Task<Template> TemplateDTOToTemplate(TemplateDTO dto)
        {
            Template template = new Template();
            template.Id = dto.Id;
            template.Name = dto.Name;
            template.TemplateFieldList = new List<TemplateField>();

            TemplateFieldResponse[] templateFieldDTOs = await GetTemplateFieldByTemplateId(dto.Id);

            int x = templateFieldDTOs.Length;   

            for (int i = 0; i <x; i++)
            {
                TemplateField templateField = new TemplateField();
                
                templateField = TemplateFieldDTOToTemplateField(  templateFieldDTOs[i].TemplateField);
                if(templateFieldDTOs[i].OptionList != null)   templateField.OptionList = OptionListDTOToOptionList(templateFieldDTOs[i].OptionList);

                template.TemplateFieldList.Add(templateField);  
            }
             

            return template;
        }

        public TemplateDTO TemplateToTemplateDTO(Template template, long idGameBundle)
        {
            TemplateDTO dto = new TemplateDTO();
            dto.Id = template.Id;
            dto.Name = template.Name;
            dto.IdGameBundle = idGameBundle;
            dto.IdTemplateFieldList = new List<long>();
            foreach (TemplateField field in template.TemplateFieldList)
            {
                dto.IdTemplateFieldList.Add(field.Id);
            }
            return dto;
        }

        // ------------------- TemplateField -------------------------
        public Awaitable<TemplateFieldDTO> CreateTemplateField(TemplateFieldDTO customObject, long idUser)
        {
            return CreateAsync("/createTemplateField/" + idUser, customObject);
        }

        public Awaitable<TemplateFieldDTO[]> CreateManyTemplateField(long id, TemplateFieldDTO[] list)
        {
            return CreateManyAsync("/createManyTemplateField/" + id, list);
        }

        public Awaitable<TemplateFieldDTO> UpdateTemplateField(TemplateFieldDTO customObject)
        {
            return UpdateAsync("/updateTemplateField", customObject);
        }

        public Awaitable<TemplateFieldDTO[]> UpdateManyTemplateField(TemplateFieldDTO[] list)
        {
            return UpadateManyAsync("/updateManyTemplateField", list);
        }

        public Awaitable<string> DeleteTemplateField(long id)
        {
            return DeleteAsync("/deleteTemplateField/" + id);
        }

        public Awaitable<TemplateFieldResponse[]> GetTemplateFieldByTemplateId(long id)
        {
            return GetAllAsync<TemplateFieldResponse> ("/getTemplateFieldByTemplateId/" + id);
        }



        public TemplateField TemplateFieldDTOToTemplateField(TemplateFieldDTO dto)
        {
            TemplateField templateField = new TemplateField();
            templateField.Id = dto.Id;
            templateField.Label = dto.Label;
            templateField.Type = dto.Type;
          
            templateField.Required = dto.Required;
            templateField.MinValue = dto.MinValue;
            templateField.MaxValue = dto.MaxValue;
            templateField.PositionX = dto.PositionX;
            templateField.PositionY = dto.PositionY;

            if(dto.IdOptionList != null)   templateField.OptionList = new OptionList { Id = (long)dto.IdOptionList };
            else templateField.OptionList = null;

            return templateField;
        }

        public TemplateFieldDTO TemplateFieldToTemplateFieldDTO(TemplateField templateField, long idTemplate)
        {
            TemplateFieldDTO dto = new TemplateFieldDTO();
            dto.Id = templateField.Id;
            dto.Label = templateField.Label;
            dto.Type = templateField.Type;
            dto.Required = templateField.Required;
            dto.MinValue = templateField.MinValue;
            dto.MaxValue = templateField.MaxValue;
            dto.PositionX = templateField.PositionX;
            dto.PositionY = templateField.PositionY;
            dto.IdOptionList = templateField.OptionList != null ? templateField.OptionList.Id : 0;
            dto.IdTemplates = idTemplate;
            return dto;
        }


    }
}

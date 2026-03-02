using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;
using Assets._Project.API.Model.Object.Game.Templates;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using UnityEditor.PackageManager;
using UnityEngine;

namespace Assets._Project.API.Service.Game.Templates
{
    class TemplateService : ApiService
    {
        private CatchError onError;
        public TemplateService() : base("template") { }

        // ------------------- CustomObject  -------------------------
        public Awaitable<CustomObjectDTO> CreateCustomObject<CustomObjectDTO>(CustomObjectDTO customObject, long idUser)
        {
            return CreateAsync("/createCustomObject/" + idUser, customObject);
        }

        public Awaitable<CustomObjectDTO> UpdateCustomObject<CustomObjectDTO>(CustomObjectDTO customObject)
        {
            return UpdateAsync("/updateCustomObject", customObject);
        }

       public Awaitable<string> DeleteCustomObject(long id)
        {
            return DeleteAsync("/deleteCustomObject/" + id);
        }

        public Awaitable<CustomObjectDTO> GetCustomObjectById<CustomObjectDTO>(long id)
        {
            return GetAsync<CustomObjectDTO>("/getCustomObjectById/" + id);
        }   

       public Awaitable<CustomObjectDTO[]> GetAllCustomObjectByGameBundleId<CustomObjectDTO>(long id)
        {
            return GetAllAsync<CustomObjectDTO>("/GetAllCustomObjectByGameBundleId/" + id);
        }

       

        public CustomObject CustomObjectDTOToCustomObject(CustomObjectDTO dto)
        {
            CustomObject customObject = new CustomObject();
            customObject.Id = dto.Id;
            customObject.Name = dto.Name;
            customObject.FieldValues = dto.FieldValues;
            customObject.CanBeInInventory = dto.CanBeInInventory;
            customObject.Price = dto.Price;
            return customObject;
        }

        public CustomObjectDTO CustomObjectToCustomObjectDTO(CustomObject customObject,long idTemplate,long idGamebundle)
        {
            CustomObjectDTO dto = new CustomObjectDTO();
            dto.Id = customObject.Id;
            dto.Name = customObject.Name;
            dto.FieldValues = customObject.FieldValues;
            dto.CanBeInInventory = customObject.CanBeInInventory;
            dto.Price = customObject.Price;
            dto.IdTemplate = idTemplate;
            dto.IdGameBundles = idGamebundle;
            return dto;

        }

        // ------------------- OptionList    -------------------------
        
        public Awaitable<OptionListDTO> CreateOptionList<OptionListDTO>(OptionListDTO customObject, long idUser)
        {
            return CreateAsync("/createOptionList/" + idUser, customObject);
        }

        public Awaitable<OptionListDTO> UpdateOptionList<OptionListDTO>(OptionListDTO customObject)
        {
            return UpdateAsync("/updateOptionList", customObject);
        }

       public Awaitable<string> DeleteOptionList(long id)
        {
            return DeleteAsync("/deleteOptionList/" + id);
        }

        public Awaitable<OptionListDTO> GetOptionListById<OptionListDTO>(long id)
        {
            return GetAsync<OptionListDTO>("/getOptionListById/" + id);
        }
        
        public Awaitable<OptionListDTO[]> GetOptionListByIdGameBundle<OptionListDTO>(long id)
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

        public OptionListDTO OptionListToOptionListDTO(OptionList optionList,long IdTemplateField,long idGameBundle)
        {
            OptionListDTO dto = new OptionListDTO();
            dto.Id = optionList.Id;
            dto.Name = optionList.Name;
            dto.Options = optionList.Options;
            dto.IdTemplateField = IdTemplateField;
            dto.IdGameBundle = idGameBundle;
            return dto;
        }

        // ------------------- Template      -------------------------
        public Awaitable<TemplateDTO> CreateTemplate<TemplateDTO>(TemplateDTO customObject, long idUser)
        {
            return CreateAsync("/createTemplate/" + idUser, customObject);
        }
        
        public Awaitable<TemplateDTO> UpdateTemplate<TemplateDTO>(TemplateDTO customObject)
        {
            return UpdateAsync("/updateTemplate", customObject);
        }

        public Awaitable<string> DeleteTemplate(long id)
        {
            return DeleteAsync("/deleteTemplate/" + id);
        }


        public Awaitable<TemplateDTO> GetTemplateById<TemplateDTO>(long id)
        {
            return GetAsync<TemplateDTO>("/getTemplateById/" + id);
        }

        

        public Template TemplateDTOToTemplate(TemplateDTO dto)
        {
            Template template = new Template();
            template.Id = dto.Id;
            template.Name = dto.Name;
            template.TemplateFieldList = new List<TemplateField>();

            foreach (long idField in dto.IdTemplateFieldList)
            {
                TemplateField field = new TemplateField();
                field.Id = idField;
                template.TemplateFieldList.Add(field);
            }

            return template;
        }

        public TemplateDTO TemplateToTemplateDTO(Template template,long idGameBundle)
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
        public Awaitable<TemplateFieldDTO> CreateTemplateField<TemplateFieldDTO>(TemplateFieldDTO customObject, long idUser)
        {
            return CreateAsync("/createTemplateField/" + idUser, customObject);
        }

        public Awaitable<TemplateFieldDTO> UpdateTemplateField<TemplateFieldDTO>(TemplateFieldDTO customObject)
        {
            return UpdateAsync("/updateTemplateField", customObject);
        }

        public Awaitable<string> DeleteTemplateField(long id)
        {
            return DeleteAsync("/deleteTemplateField/" + id);
        }

        public Awaitable<TemplateFieldDTO> GetTemplateFieldByTemplateId<TemplateFieldDTO>(long id)
        {
            return GetAsync<TemplateFieldDTO>("/getTemplateFieldByTemplateId/" + id);
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

            templateField.OptionList = new OptionList { Id = dto.IdOptionList};

            return templateField;
        }

        public TemplateFieldDTO TemplateFieldToTemplateFieldDTO(TemplateField templateField,List<long> idTemplate)
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

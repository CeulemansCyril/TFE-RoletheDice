using System;
using System.Collections;
using UnityEngine;

namespace Assets._Project.API.Model.DTO.GameDTO.TemplateDTO
{
	public class CustomObjectAttributeDTO
	{
        public long Id {  get; set; }

        public long IdCustomObject { get; set; }

        public long IdTemplateField { get; set; }

        public string Value { get; set; }

        public string Type { get; set; }

        public CustomObjectAttributeDTO() { }
        public CustomObjectAttributeDTO(long id,long idCustomObject,long idTemplateField,string value,string type) {
        
            Id=id;
            IdCustomObject=idCustomObject;
            IdTemplateField=idTemplateField;
            Value = value;
            Type = type;
        
        }

    }
}
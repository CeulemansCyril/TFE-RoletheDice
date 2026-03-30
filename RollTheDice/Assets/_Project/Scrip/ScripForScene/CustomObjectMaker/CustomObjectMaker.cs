using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;
using Assets._Project.API.Model.Object.Game.Money;
using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.API.Service.Game.Templates;
using Assets._Project.Localization;
using Assets._Project.Scrip.ScripForScene.Bundle;
using Assets._Project.Scrip.ScripForScene.Custom;
using Assets._Project.Scrip.ScripForScene.Login;
using Assets._Project.Scrip.ScripUI.RenameField;
using System;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.CustomObjectMaker
{
    class CustomObjectMaker : MonoBehaviour
    {
        [SerializeField] private GameObject fieldPrefab;
        [SerializeField] private UnityEngine.UI.Button SaveButton;
        [SerializeField] private UnityEngine.UI.Button CloseButton;
        [SerializeField] private RenameField RenameField;

        [SerializeField] private Toggle IsCanBeInvotory;
        [SerializeField] private ValueOption valueOption;


        private RectTransform rectTransform;

        private bool IsSave = true;
        private bool CantClose = false;

        private CustomObject currentCustomObject;
        private TemplateService templateService;

        private List<FieldData> fields = new List<FieldData>();
        public void Awake()
        {
            rectTransform = GetComponent<RectTransform>();

        }

        private void Start()
        {
            if (SceneData.HasData("CustomToCreate"))
            {
                currentCustomObject = SceneData.GetData<CustomObject>("CustomToCreate");

                SceneData.RemoveData("CustomToCreate");
            }
            else if (SceneData.HasData("CustomToModify"))
            {
                currentCustomObject = SceneData.GetData<CustomObject>("CustomToModify");

                SceneData.RemoveData("CustomToModify");
            }


            SaveButton.onClick.AddListener(OnSaveButtonClicked);
            CloseButton.onClick.AddListener(OnCloseButton);
            RenameField.SetText(currentCustomObject.Name);

            templateService = new TemplateService();

            LoadFields(currentCustomObject.Template.TemplateFieldList);
            valueOption.SetValue(currentCustomObject.Price);
            IsCanBeInvotory.isOn = currentCustomObject.CanBeInInventory;


        }


        private void ClearAllFields()
        {

            foreach (Transform child in transform)
            {
                Destroy(child.gameObject);
            }
        }

       


        private void LoadFields(List<TemplateField> templateField)
        {
            if (rectTransform.childCount > 0) ClearAllFields();

            if (templateField == null)
            {
                Debug.LogError("templateField est null !");
                return;
            }
            fields.Clear();
             CustomObjectAttributeDTO[] Attributes = currentCustomObject.Attributes;

            if (Attributes == null)
            {
                Debug.LogWarning("Attributes est null → initialisation liste vide");
                Attributes = new  CustomObjectAttributeDTO[0];
            }

            foreach (CustomObjectAttributeDTO attr in Attributes)
            {
                Debug.Log($"ATTR → TemplateFieldId: {attr.IdTemplateField} | Value: {attr.Value}");
            }

           

            foreach (TemplateField fieldData in templateField)
            {
                GameObject field = Instantiate(fieldPrefab, transform);


                FieldData fieldUI = field.GetComponent<FieldData>();
                fieldUI.OnValueChanged += ValidateAllFields;
                fields.Add(fieldUI);


                if (fieldUI != null)
                {
                    CustomObjectAttributeDTO custom = null;

                    if (fieldData.Id != null)
                    {
                        custom = Attributes.FirstOrDefault(c => c.IdTemplateField == fieldData.Id);
                    }

                    Debug.Log($"FIELD → Id: {fieldData.Id} | Label: {fieldData.Label}");
                    fieldUI.Init(fieldData, custom);
                }
                else
                {
                    Debug.LogError("FieldData component missing on prefab");
                }
            }
        }

        private void ValidateAllFields()
        {
            bool allValid = true;

            foreach (var field in fields)
            {
                if (!field.IsValid())
                {
                    allValid = false;
                }
            }

            SaveButton.interactable = allValid;
            CloseButton.interactable = allValid;
        }

        private void OnClose()
        {
            SceneLoader.Instance.LoadScene(Scene.Scene.MainMenuCreate);
        }

        private void OnSaveButtonClicked()
        {
            SaveButton.interactable = false;
            SaveCustomObject();

            SaveButton.interactable = true;
        }

        private void OnCloseButton()
        {
            if (CantClose) return;
            if (!IsSave)
            {
                bool userConfirmed = false;

                PopUpManager.Instance.ShowConfirmPopUp(LocalizationControllers.Instance.GetLocalizedValue("PopUpSave.title"), LocalizationControllers.Instance.GetLocalizedValue("PopUpSave.Message"),
                    () => OnConfirmCloseWithSave(), () => OnConfirmCloseWithoutSave());

            }
            else
            {
                OnClose();
            }
        }

        private void OnConfirmCloseWithSave()
        {
            SaveCustomObject();
            OnClose();
        }

        private void OnConfirmCloseWithoutSave()
        {
            OnClose();
        }

        private async void SaveCustomObject()
        {
            CantClose = false;

            string newTitle = RenameField.GetTitle();

            if (!string.IsNullOrWhiteSpace(newTitle) && newTitle != currentCustomObject.Name)
                currentCustomObject.Name = newTitle;

            currentCustomObject.Price = valueOption.GetValue();
            currentCustomObject.CanBeInInventory = IsCanBeInvotory.isOn;
            Value price = valueOption.GetValue();
            Debug.Log($"Price → Amount: {price.Amount} | Currency: {price.Currency}");
            Debug.Log($"CanBeInInventory → {currentCustomObject.CanBeInInventory}");

            List<FieldData> fields = new List<FieldData>(GetComponentsInChildren<FieldData>());

            bool allValid = true;

            foreach (FieldData field in fields)
            {
                if (!field.IsValid())
                {
                    allValid = false;
                }
            }

            if (!allValid)
            {
                CantClose = true;

                return;
            }
            int fieldSize = fields.Count;
            
            currentCustomObject.Attributes = new CustomObjectAttributeDTO[fieldSize];
            int idx = 0;
            foreach (FieldData field in fields)
            {
                object value = field.GetData();
                if (value != null && idx < currentCustomObject.Attributes.Length)
                {
                    currentCustomObject.Attributes[idx++] = new CustomObjectAttributeDTO
                    {
                        IdTemplateField = field.templateField.Id,
                        IdCustomObject = currentCustomObject.Id,
                        Value = value.ToString(),
                        Type = field.templateField.Type
                    }; 
                }
            }


            foreach (CustomObjectAttributeDTO attr in currentCustomObject.Attributes)
            {
                Debug.Log($"ATTR → TemplateFieldId: {attr.IdTemplateField} | Value: {attr.Value}");
            }


            CustomObjectDTO dto = templateService.CustomObjectToCustomObjectDTO(
                currentCustomObject,
                currentCustomObject.Template.Id,
                BundleSession.Intance.Bundle.Id
            );

            if (currentCustomObject.Id != null) 
            {
                await templateService.UpdateCustomObject(dto);
              
            }
             
            else {
                dto = await templateService.CreateCustomObject(dto, UserSession.Intance.UserID);
     
                currentCustomObject.Id = dto.Id;
            }


            var updateField = currentCustomObject.Attributes
             .Where(a => a.Id != 0)
             .ToArray();

            var createField = currentCustomObject.Attributes
                .Where(a => a.Id == 0)
                .ToArray();

            CustomObjectAttributeDTO[] updatedResult = Array.Empty<CustomObjectAttributeDTO>();
            CustomObjectAttributeDTO[] createdResult = Array.Empty<CustomObjectAttributeDTO>();

            if (updateField.Length > 0)
            {
                updatedResult = await templateService.UpdateManyCustomObjectAttribute(updateField);
            }

            if (createField.Length > 0)
            {
                createdResult = await templateService.CreateManyCustomObjectAttribute(createField);
            }

            var finalAttributes = updatedResult
            .Concat(createdResult)
            .ToArray();

            currentCustomObject.Attributes = finalAttributes;

            CantClose = false;
        }


    }
}

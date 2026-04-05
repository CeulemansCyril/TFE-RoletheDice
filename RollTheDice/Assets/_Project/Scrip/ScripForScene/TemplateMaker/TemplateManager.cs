using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;
using Assets._Project.API.Model.DTO.ReponseDTO;
using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.API.Service.Game.Templates;
using Assets._Project.Localization;
using Assets._Project.Localization.Localization;
using Assets._Project.Scrip.Scene;
using Assets._Project.Scrip.ScripForScene.Bundle;
using Assets._Project.Scrip.ScripForScene.Login;
using System.Collections.Generic;
using System.Threading.Tasks;
using TMPro;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.UI;
using static UnityEngine.Rendering.DebugUI;

public class TemplateManager : MonoBehaviour
{
    [SerializeField] private UnityEngine.UI.Button SaveButton;
    [SerializeField] private UnityEngine.UI.Button CloseButton;
    [SerializeField] private TMP_Text NameTemplate;
    [SerializeField] private BuildPanellDrop buildPanell;
    public Template TemplateActive { get; set; }
    public bool IsSave { get; set; }

    private TemplateService templateService;
    private bool isNewTemplate = false;
    private async void Start()
    {
        try
        {
            await Init();
        }
        catch (System.Exception e)
        {
            Debug.LogError(e);
        }
    }


 

    private async Task Init()
    {
        IsSave = false;
        SaveButton.onClick.AddListener(OnSaveButtonClicked);
        CloseButton.onClick.AddListener(OnCloseButton);

        templateService = new TemplateService();

        if (SceneData.HasData("TemplateToModify"))
        {
            Template templateToModify = SceneData.GetData<Template>("TemplateToModify");
            TemplateActive = templateToModify;

     

            TemplateFieldResponse[] fieldDTOs =
                await templateService.GetTemplateFieldByTemplateId(templateToModify.Id);

                  

            if (fieldDTOs == null)
            {
                Debug.LogError("fieldDTOs is NULL");
                return;
            }

            TemplateActive.TemplateFieldList.Clear();

            foreach (var fieldDTO in fieldDTOs)
            {
                TemplateField templateField =
                    templateService.TemplateFieldDTOToTemplateField(fieldDTO.TemplateField);

           
                if (fieldDTO.OptionList != null)
                {
                    templateField.OptionList =
                        templateService.OptionListDTOToOptionList(fieldDTO.OptionList);
                }

                TemplateActive.TemplateFieldList.Add(templateField);
            }

          

            LoadsTemplateField(TemplateActive);

            SceneData.RemoveData("TemplateToModify");
        }
    }


    private async void OnSaveButtonClicked()
    {
        await OnSave();
    }

    private void OnCloseButton()
    {
        if(!IsSave)
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

    private async void OnConfirmCloseWithSave()
    {
        await OnSave();
        OnClose();
    }

    private void OnConfirmCloseWithoutSave()
    {
        OnClose();
    }


    private async Task OnSave()
    {
 
        TemplateActive.Name = NameTemplate.text;
 
        List<TemplateField> templateFields = buildPanell.GetAllField();

            TemplateFieldDTO[] fieldDTOs =
                new TemplateFieldDTO[templateFields.Count];

            for (int i = 0; i < templateFields.Count; i++)
            {
                fieldDTOs[i] = templateService.TemplateFieldToTemplateFieldDTO(templateFields[i],TemplateActive.Id);
            }
       


        TemplateActive.TemplateFieldList = templateFields;

        TemplateDTO templateDTO = templateService.TemplateToTemplateDTO(TemplateActive,BundleSession.Intance.Bundle.Id);

        try
        {
            if (isNewTemplate)
            {
                await templateService.CreateTemplate(templateDTO, UserSession.Intance.UserID);
                await templateService.CreateManyTemplateField(UserSession.Intance.UserID, fieldDTOs);
            }
            else
            {
                await templateService.UpdateTemplate(templateDTO);
                await templateService.UpdateManyTemplateField(fieldDTOs);
            }

            IsSave = true;
        }
        catch (System.Exception ex)
        {
            Debug.LogError("Error saving template or template fields: " + ex);
            IsSave = false;
        }
    }

    private void OnClose()
    {
        SceneLoader.Instance.LoadScene(Scene.MainMenuCreate);
    }

    public void LoadsTemplateField(Template template)
    {
        NameTemplate.text = template.Name;
        
     
        if (template.TemplateFieldList != null) buildPanell.LoadFields(template.TemplateFieldList);
    }

}

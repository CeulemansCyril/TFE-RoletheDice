using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.API.Service.Game.Templates;
using Assets._Project.Localization;
using Assets._Project.Localization.Localization;
using Assets._Project.Scrip.Scene;
using System.Collections.Generic;
using TMPro;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.UI;

public class TemplateManager : MonoBehaviour
{
    [SerializeField] private Button SaveButton;
    [SerializeField] private Button CloseButton;
    [SerializeField] private TMP_Text NameTemplate;

    public Template TemplateActive { get; set; }
    public bool IsSave { get; set; }

    private TemplateService templateService;

    private void Start()
    {
        IsSave = false;
        SaveButton.onClick.AddListener(OnSaveButtonClicked);
        CloseButton.onClick.AddListener(OnCloseButton);

        templateService = new TemplateService();

       if(SceneData.HasData("TemplateToModify"))
       {
           Template templateToModify = SceneData.GetData<Template>("TemplateToModify");
            TemplateActive = templateToModify;
            LoadsTemplateField(templateToModify);
           SceneData.RemoveData("TemplateToModify");
       }else if (SceneData.HasData("TemplateToCreate")) { 
           Template templateToCreate = SceneData.GetData<Template>("TemplateToCreate");
           Debug.Log("Creating new template: " + templateToCreate.Name);
            TemplateActive = templateToCreate;
            NameTemplate.text = TemplateActive.Name;
            SceneData.RemoveData("TemplateToCreate");
        }
          
     
    }

  

    private void OnSaveButtonClicked()
    {
        
        OnSave();
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

    private void OnConfirmCloseWithSave()
    {
        OnSave();
        OnClose();
    }

    private void OnConfirmCloseWithoutSave()
    {
        OnClose();
    }


    private void OnSave()
    {
 
        TemplateActive.Name = NameTemplate.text;

        List<TemplateField> templateFields = GetComponent<BuildPanellDrop>().GetAllField();

        TemplateActive.TemplateFieldList = templateFields;

         //TODO : Save field 

        IsSave = true;
    }

    private void OnClose()
    {
        SceneLoader.Instance.LoadScene(Scene.MainMenuCreate);
    }

    public void LoadsTemplateField(Template template)
    {
    
        NameTemplate.text = template.Name;
        GetComponent<BuildPanellDrop>().LoadFields(template.TemplateFieldList);
    }

}

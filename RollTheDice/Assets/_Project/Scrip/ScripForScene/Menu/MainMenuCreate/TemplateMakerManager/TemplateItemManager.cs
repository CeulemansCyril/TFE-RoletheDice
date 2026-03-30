
using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;
using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.API.Service.Game.Templates;
using Assets._Project.Localization;
using Assets._Project.Scrip.Scene;
using Assets._Project.Scrip.ScripForScene.Bundle;
using Assets._Project.Scrip.ScripForScene.Login;
using System.Collections.Generic;
using System.Threading.Tasks;
using TMPro;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.UI;

public class TemplateItemManager : MonoBehaviour
{
    [SerializeField] private Transform templateItemParent;
    [SerializeField] private GameObject templateItemPrefab;
    [SerializeField] private Button CreateTemplateButton;

    [SerializeField] private TMP_Text NoTemplateText;
    [SerializeField] private TMP_InputField searchBar;

    private List<Template> templates = new List<Template>();
    private TemplateService templateService;

    private void Start()
    {
        CreateTemplateButton.onClick.AddListener(() => CreateTemplateClick());
        searchBar.onValueChanged.AddListener(OnSearchChanged);
        NoTemplateText.gameObject.SetActive(false);
        templateService = new TemplateService();
       
        LoadList();
    }
    private async void LoadList() {

        try
        {
            TemplateDTO[] dto = await templateService.GetAllTemplateByGameBundleId(BundleSession.Intance.Bundle.Id);

            if (dto != null) {
                foreach (TemplateDTO templateDTO in dto)
                {
                    Template template =await templateService.TemplateDTOToTemplate(templateDTO);
                    templates.Add(template);
                }
            }
        }
        catch (System.Exception) { }

        if (templateItemPrefab != null || templates != null || templates.Count >0 )
        {
            foreach (Template template in templates)
            {
                CreateTemplateItem(template);
            }
        }
        else
        {
           NoTemplateText.gameObject.SetActive(true);
            
        }
    
    }

    private void CreateTemplateItem(Template template)
    {
        GameObject templateItemObject = Instantiate(templateItemPrefab, templateItemParent);
        TemplateItem templateItem = templateItemObject.GetComponent<TemplateItem>();
        if (templateItem != null)
        {
            templateItem.Template = template;
            templateItem.TemplateName.text = template.Name;
        }
        templateItem.DeleteButon.onClick.AddListener(() => DeleteClick(templateItem));
        templateItem.ModifButon.onClick.AddListener(() => ModifyClick(templateItem));
    }

    private void DeleteClick(TemplateItem templateItem)
    {
        PopUpManager.Instance.ShowConfirmPopUp(
            LocalizationControllers.Instance.GetLocalizedValue("PopUpDelTemplate.title"),
            LocalizationControllers.Instance.GetLocalizedValue("PopUpDelTemplate.Message"),
            () =>
            {
                Template templateToRemove = templateItem.Template;

                if (templates.Contains(templateToRemove))
                {
                    templates.Remove(templateToRemove);

                    templateService
                        .DeleteTemplate(templateToRemove.Id)
                        .GetAwaiter()
                        .GetResult();

                    Destroy(templateItem.gameObject);
                }
            },
            () => { }
        );
    }

   

    private void ModifyClick(TemplateItem templateItem)
    {
            //TODO : Ajouter une modification du Template de la DB
            SceneData.SetData("TemplateToModify", templateItem.Template);
            SceneLoader.Instance.LoadScene(Scene.TemplateMaker);

    }

    private void CreateTemplateClick()
    {      
        string name = "";
        PopUpManager.Instance.ShowInputPopUp(LocalizationControllers.Instance.GetLocalizedValue("PopUpCreatTemplate.title"), LocalizationControllers.Instance.GetLocalizedValue("PopUpCreatTemplate.Message"), NameTemplateConfirm,null); 
    }

    private async void NameTemplateConfirm(string name)
    {

        if(!string.IsNullOrEmpty(name)) {

            if (templates.Exists(t => t.Name.ToLower() == name.ToLower()))
            {
                //TODO : Ajouter un message d'erreur disant que le nom existe déjà 
                // PopUpManager.Instance.("Erreur", "Un template avec ce nom existe déjà.");
                return;
            }



            TemplateDTO templateDTO = new TemplateDTO();
           
            templateDTO.Name = name;
            templateDTO.IdGameBundle = BundleSession.Intance.Bundle.Id;

            templateDTO = await templateService.CreateTemplate(templateDTO, UserSession.Intance.UserID);

            Template template = await templateService.TemplateDTOToTemplate(templateDTO);

            SceneData.SetData("TemplateToCreate", template);
            SceneLoader.Instance.LoadScene(Scene.TemplateMaker);
        }
    }

    public void Refresh()
    {
        bool hasItems = templateItemParent.childCount > 1;
        NoTemplateText.gameObject.SetActive(!hasItems);
    }

    private void OnSearchChanged(string searchText)
    {
        searchText = searchText.ToLower();
        ClearList(); 

        bool hasItems = false;
        foreach (Template template in templates)
        {
            if (string.IsNullOrEmpty(searchText) || template.Name.ToLower().Contains(searchText))
            {
                CreateTemplateItem(template);
                hasItems = true;
            }
        }
        NoTemplateText.gameObject.SetActive(!hasItems);
    }

    private void ClearList()
    {
        for (int i = templateItemParent.childCount - 1; i >= 0; i--)
        {
            if(templateItemParent.GetChild(i).gameObject != NoTemplateText.gameObject) Destroy(templateItemParent.GetChild(i).gameObject);
        }
    }

   

}

using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;
using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.API.Service.Game.Templates;
using Assets._Project.Localization;
using Assets._Project.Scrip.Scene;
using Assets._Project.Scrip.ScripForScene.Bundle;
using Assets._Project.Scrip.ScripForScene.Login;
using Assets._Project.Scrip.ScripUI.RenameField;
using System;
using System.Collections.Generic;
using System.Linq;
using TMPro;

using UnityEngine;
using UnityEngine.UI;

public class CustomObjectManager : MonoBehaviour
{
    [SerializeField] private Transform customObjectItemParent;
    [SerializeField] private GameObject customObjectItemPrefab;
    [SerializeField] private Button CreateTemplateButton;
 

    [SerializeField] private TMP_Text NoCustomObjectText;
 
    [SerializeField] private TMP_InputField searchBar;
 

    private List<CustomObject> customObjects = new List<CustomObject>();

    public Action ClickOnCreated; 

    private TemplateService templateService;

    private void Start()
    {
        CreateTemplateButton.onClick.AddListener(() => CreateTemplateClick());
        searchBar.onValueChanged.AddListener(OnSearchChanged);
        
        NoCustomObjectText.gameObject.SetActive(false);
        templateService = new TemplateService();

        LoadList();
    }
    private async void LoadList()
    {


        CustomObjectDTO[] dto = await templateService?.GetAllCustomObjectByGameBundleId(BundleSession.Intance.Bundle.Id);

        if (dto != null)
        {
            foreach (CustomObjectDTO customObjectDTO in dto)
            {
                CustomObject customObject = await templateService.CustomObjectDTOToCustomObjectAsync(customObjectDTO);
                customObjects.Add(customObject);
            }
        }


        if (customObjectItemPrefab != null || customObjects != null || customObjects.Count > 0)
        {
            foreach (CustomObject custom in customObjects)
            {
                CreateTemplateItem(custom);
            }
        }
        else
        {
            NoCustomObjectText.gameObject.SetActive(true);

        }

    }

   

    private void CreateTemplateItem(CustomObject cust)
    {
        GameObject customObjectItem = Instantiate(customObjectItemPrefab, customObjectItemParent);
        CustomObjectItem customItem = customObjectItem.GetComponent<CustomObjectItem>();
        if (customObjectItem != null)
        {
            customItem.Custom = cust;
            customItem.SetTitle(cust.Name+" | " + cust.Template.Name);

        }
        customItem.DeleteButon.onClick.AddListener(() => DeleteClick(customItem));
        customItem.ModifButon.onClick.AddListener(() => ModifyClick(customItem));
    }

    private void DeleteClick(CustomObjectItem custItem)
    {
        PopUpManager.Instance.ShowConfirmPopUp(
            LocalizationControllers.Instance.GetLocalizedValue("PopUpDelCustom.title"),
           LocalizationControllers.Instance.GetLocalizedValue("PopUpDelCustom.Message"),
            async () =>
            {
                CustomObject costToRemove = custItem.Custom;

                if (customObjects.Contains(costToRemove))
                {
                    customObjects.Remove(costToRemove);

                    await templateService
                        .DeleteCustomObject(costToRemove.Id);


                    Destroy(custItem.gameObject);
                    Refresh();
                }
            },
            () => { }
        );
    }



    private async void ModifyClick(CustomObjectItem customItem)
    {
        CustomObject customToModify = customItem.Custom;
        CustomObjectAttributeDTO[] attrs = await templateService.GetCustomObjectAttributeByCustomObjectId(customToModify.Id);
     
        customToModify.Attributes = attrs;
         

        SceneData.SetData("CustomToModify", customToModify);
        SceneLoader.Instance.LoadScene(Scene.CustomObjectMaker);

    }

    private void CreateTemplateClick()
    {
        ClickOnCreated.Invoke(); 
    }

    

    public void Refresh()
    {
        bool hasItems = customObjectItemParent.childCount > 1;
        NoCustomObjectText.gameObject.SetActive(!hasItems);
    }

    private void OnSearchChanged(string searchText)
    {
        searchText = searchText.ToLower();
        ClearList();

        bool hasItems = false;
        foreach (CustomObject template in customObjects)
        {
            if (string.IsNullOrEmpty(searchText) || template.Name.ToLower().Contains(searchText))
            {
                CreateTemplateItem(template);
                hasItems = true;
            }
        }
        NoCustomObjectText.gameObject.SetActive(!hasItems);
    }

    private void ClearList()
    {
        for (int i = customObjectItemParent.childCount - 1; i >= 0; i--)
        {
            if (customObjectItemParent.GetChild(i).gameObject != NoCustomObjectText.gameObject) Destroy(customObjectItemParent.GetChild(i).gameObject);
        }
    }



}

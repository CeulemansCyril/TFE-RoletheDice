using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.Scrip.Scene;
using System.Collections.Generic;
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

    private void Start()
    {
        CreateTemplateButton.onClick.AddListener(() => CreateTemplateClick());
        searchBar.onValueChanged.AddListener(OnSearchChanged);
        NoTemplateText.gameObject.SetActive(false);
        LoadTestTemplates();
        LoadList();
    }
    private void LoadList() {

        //TODO : Ajouter une recuperation des Template de la DB


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
        bool flag = false;
        PopUpManager.Instance.ShowConfirmPopUp("Supprimer Template", "Es-tu sûr de vouloir supprimer ce template ?", () => flag = true, () => flag = false);

        if(flag) {
            Template templateToRemove = templateItem.Template;
             if (templates.Contains(templateToRemove))
             {
                    templates.Remove(templateToRemove);
                //TODO : Ajouter une suppression du Template de la DB
                Destroy(templateItem.gameObject);
             }
        }
    }

    private void ModifyClick(TemplateItem templateItem)
    {
            //TODO : Ajouter une modification du Template de la DB
            SceneData.SetData("TemplateToModify", templateItem.Template);
            SceneLoader.Instance.LoadScene(Scene.TemplateMaker);

    }

    private void CreateTemplateClick()
    {
        //TODO : Ajouter une creation du Template de la DB
        
        string name = "";

        //TODO : Ajouter une verification du nom du template (ex: pas de caractere speciaux, pas de nom deja existant, etc...) + traduction 
        PopUpManager.Instance.ShowInputPopUp("Créer Template", "Entrez le nom du template :", NameTemplateConfirm,null);
        

     
    }

    private void NameTemplateConfirm(string name)
    {
        Debug.Log("Name entered: " + name);
        if(!string.IsNullOrEmpty(name)) {
            Template template = new Template();
            template.Name = name;
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

    private void LoadTestTemplates()
    {
        templates = new List<Template>
    {
        new Template { Name = "Template Combat" },
        new Template { Name = "Template Dialogue" },
        new Template { Name = "Template Inventaire" },
        new Template { Name = "Template Quête" },
        new Template { Name = "Template Personnage" },
        new Template { Name = "Template Environnement" },
        new Template { Name = "Template UI" },
        new Template { Name = "Template Boss Final" },
        new Template { Name = "Template Tutoriel" },
        new Template { Name = "Template Sauvegarde" },

        // Cas intéressants pour tester la search
        new Template { Name = "combat_avancé" },
        new Template { Name = "Dialogue PNJ" },
        new Template { Name = "quête_principale" },
        new Template { Name = "UI - Mobile" },
        new Template { Name = "UI - Desktop" },
        new Template { Name = "TEST template" },
        new Template { Name = "test rapide" },
        new Template { Name = "123_Template_Debug" }
    };
    }

}

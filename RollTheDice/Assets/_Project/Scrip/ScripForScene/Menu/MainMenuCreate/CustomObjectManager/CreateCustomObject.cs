using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;
using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.API.Service.Game.Templates;
using Assets._Project.Scrip.ScripForScene.Bundle;
using Assets._Project.Scrip.ScripForScene.CustomObjectMaker;
using Assets._Project.Scrip.ScripForScene.Login;
using Assets._Project.Scrip.ScripUI.RenameField;
using System;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.Menu.MainMenuCreate.CreateCustomObject
{
    public class CreateCustomObject : MonoBehaviour
    {
        [SerializeField] private RenameField renameField;

        [SerializeField] private TMP_Text NoCustomObjectText;

        [SerializeField] private TMP_InputField searchBar;
        [SerializeField] private Transform templateItemParent;
        [SerializeField] private GameObject templateItemPrefab;

        [SerializeField] private Transform templateItemWaiting;

        [SerializeField] private Button valided;
        [SerializeField] private Button back;

        private List<Template> templates = new List<Template>();
        private TemplateService templateService;
        private Template selectedTemplate = new Template();

        public Action OnBack;


        void Start()
        {
            templateService = new TemplateService();
            renameField.SetText("Template test");
            searchBar.onValueChanged.AddListener(OnSearchChanged);
            valided.onClick.AddListener(Created);
            back.onClick.AddListener(CallBack);
            LoadList();
        }

        private async void LoadList()
        {

            try
            {
                TemplateDTO[] dto = await templateService.GetAllTemplateByGameBundleId(BundleSession.Intance.Bundle.Id);

                if (dto != null)
                {
                    foreach (TemplateDTO templateDTO in dto)
                    {
                        Template template = await templateService.TemplateDTOToTemplate(templateDTO);
                        templates.Add(template);
                    }
                }
            }
            catch (System.Exception) { }

            if (templateItemPrefab != null || templates != null || templates.Count > 0)
            {
                foreach (Template template in templates)
                {
                    CreateTemplateItem(template);
                }
            }
            else
            {
                NoCustomObjectText.gameObject.SetActive(true);

            }

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
            NoCustomObjectText.gameObject.SetActive(!hasItems);
        }

        private void CreateTemplateItem(Template template)
        {
            GameObject templateItemObject = Instantiate(templateItemPrefab, templateItemParent);
            ButTemplate templateItem = templateItemObject.GetComponent<ButTemplate>();
            if (templateItem != null)
            {
                templateItem.Init(template);
                templateItem.ButCallback += OnClickTemplate;
            }

        }

        private void ClearList()
        {
            for (int i = templateItemParent.childCount - 1; i >= 0; i--)
            {
                if (templateItemParent.GetChild(i).gameObject != NoCustomObjectText.gameObject) Destroy(templateItemParent.GetChild(i).gameObject);
            }
        }

        private void OnClickTemplate(ButTemplate but)
        {
            if (templateItemWaiting.childCount > 0) Destroy(templateItemWaiting.GetChild(0).gameObject);
            selectedTemplate = new Template();

            GameObject templateItemObject = Instantiate(templateItemPrefab, templateItemWaiting);
            ButTemplate templateItem = templateItemObject.GetComponent<ButTemplate>();
            if (templateItem != null)
            {
                templateItem.Init(but.GetTemplate());
                templateItem.DisableButton();
            }
            selectedTemplate = but.GetTemplate();
            but.DestroyThis();
        }


        private async void Created()
        {
            CustomObjectDTO custom = new CustomObjectDTO();
            custom.Name = renameField.GetTitle();
            custom.Id = -1;
            custom.IdGameBundles = BundleSession.Intance.Bundle.Id;
            custom.IdTemplate = selectedTemplate.Id;

            custom = await templateService.CreateCustomObject(custom, UserSession.Intance.UserID);

            CustomObject customObject = await templateService.CustomObjectDTOToCustomObjectAsync(custom);

            SceneData.SetData("CustomToCreate", customObject);
            SceneLoader.Instance.LoadScene(Scene.Scene.CustomObjectMaker);

        }

        private void CallBack()
        {
            OnBack.Invoke();
        }
    }
}
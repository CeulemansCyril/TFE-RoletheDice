
using Assets._Project.API.Model.DTO.GameDTO;
using Assets._Project.API.Model.Object.Game;
using Assets._Project.API.Service.Game;
using Assets._Project.Localization;
using Assets._Project.Scrip.Scene;
using Assets._Project.Scrip.ScripForScene.Bundle;
using Assets._Project.Scrip.ScripForScene.Login;
using System.Collections.Generic;
using TMPro;

using UnityEngine;
using UnityEngine.UI;

public class BundleItemManager : MonoBehaviour
{
    [SerializeField] private Transform BundleItemParent;
    [SerializeField] private GameObject BundleItemPrefab;
    [SerializeField] private Button CreateTemplateButton;

    [SerializeField] private TMP_Text NoTemplateText;
    [SerializeField] private TMP_InputField searchBar;

    [SerializeField] private GameObject MainContnent;
    [SerializeField] private GameObject MenuBundlePan;

    [SerializeField] private Button CloseButton;

    private List<GameBundle> bundle = new List<GameBundle>();
    private GameBundleService bundleService;

    private void Start()
    {
        CreateTemplateButton.onClick.AddListener(() => CreateTemplateClick());
        CloseButton.onClick.AddListener(() => Close());
        searchBar.onValueChanged.AddListener(OnSearchChanged);
        NoTemplateText.gameObject.SetActive(false);
        bundleService = new GameBundleService();

        LoadList();
    }
    private async void LoadList()
    {

        try
        {
            GameBundleDTO[] dto =
                  await bundleService.GetGameBundlesByUserId(
                      UserSession.Intance.UserID
                  );

            Debug.Log("DTO length: " + (dto != null ? dto.Length.ToString() : "null"));

            if (dto != null)
            {
                foreach (GameBundleDTO bundleDTO in dto)
                {
                    GameBundle gameBundle = bundleService.GameBundleDTOToGameBundle(bundleDTO);
                    bundle.Add(gameBundle);
                }
            }
        }
        catch (System.Exception e)
        {
            Debug.Log("Exception : " + e);
        }

        if (BundleItemPrefab != null || bundle != null || bundle.Count > 0)
        {
            foreach (GameBundle gam in bundle)
            {
                CreateTemplateItem(gam);
            }
        }
        else
        {
            NoTemplateText.gameObject.SetActive(true);

        }

    }

    private void CreateTemplateItem(GameBundle gameBundle)
    {
        GameObject bundleItemObject = Instantiate(BundleItemPrefab, BundleItemParent);

        BundleItem bundleItem = bundleItemObject.GetComponent<BundleItem>();

        if (bundleItem != null)
        {
            bundleItem.setBundle(gameBundle);
        }
        bundleItem.DeleteButon.onClick.AddListener(() => DeleteClick(bundleItem));
        bundleItem.ModifButon.onClick.AddListener(() => ModifyClick(bundleItem));
    }

    private void DeleteClick(BundleItem bundleItem)
    {
        PopUpManager.Instance.ShowConfirmPopUp(
            LocalizationControllers.Instance.GetLocalizedValue("PopUpDelBook.title"),
           LocalizationControllers.Instance.GetLocalizedValue("PopUpDelBook.Message"),
            async () =>
            {
                GameBundle bookToRemove = bundleItem.Bundle;

                if (bundle.Contains(bookToRemove))
                {
                    bundle.Remove(bookToRemove);

                    await bundleService
                         .DeleteGameBundle(bookToRemove.Id)
                         ;

                    Destroy(bundleItem.gameObject);
                }
            },
            () => { }
        );
    }



    private void ModifyClick(BundleItem bookItem)
    {

        BundleSession.Intance.Bundle = bookItem.Bundle;
        SceneLoader.Instance.LoadScene(Scene.MainMenuCreate);

    }

    private void CreateTemplateClick()
    {
        string name = "";
        PopUpManager.Instance.ShowInputPopUp(LocalizationControllers.Instance.GetLocalizedValue("PopUpCreatBundle.title"), LocalizationControllers.Instance.GetLocalizedValue("PopUpCreatBundle.Message"), NameTemplateConfirmAsync, null);
    }

    private async void NameTemplateConfirmAsync(string name)
    {

        if (!string.IsNullOrEmpty(name))
        {

            if (this.bundle.Exists(t => t.Name.ToLower() == name.ToLower()))
            {
                //TODO : Ajouter un message d'erreur disant que le nom existe déjà 
                // PopUpManager.Instance.("Erreur", "Un template avec ce nom existe déjà.");
                return;
            }



            GameBundleDTO bundleDTO = new GameBundleDTO();
            bundleDTO.Name = name;
            bundleDTO.IdCreator = UserSession.Intance.UserID;

            GameBundleDTO createdDTO = await bundleService.CreateGameBundle(bundleDTO, UserSession.Intance.UserID);


            GameBundle bundle =
                bundleService.GameBundleDTOToGameBundle(createdDTO);

            BundleSession.Intance.Bundle = bundle;
            SceneLoader.Instance.LoadScene(Scene.MainMenuCreate);
        }
    }

    public void Refresh()
    {
        bool hasItems = BundleItemParent.childCount > 1;
        NoTemplateText.gameObject.SetActive(!hasItems);
    }

    private void OnSearchChanged(string searchText)
    {
        searchText = searchText.ToLower();
        ClearList();

        bool hasItems = false;
        foreach (GameBundle template in bundle)
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
        for (int i = BundleItemParent.childCount - 1; i >= 0; i--)
        {
            if (BundleItemParent.GetChild(i).gameObject != NoTemplateText.gameObject) Destroy(BundleItemParent.GetChild(i).gameObject);
        }
    }

    private void Close()
    {
        ClearList();
        MenuBundlePan.SetActive(false);
        MainContnent.SetActive(true);

    }

    public void RefreshView()
    {
        if (BundleItemPrefab != null || bundle != null || bundle.Count > 0)
        {
            foreach (GameBundle gam in bundle)
            {
                CreateTemplateItem(gam);
            }
        }
        else
        {
            NoTemplateText.gameObject.SetActive(true);

        }


    }
}

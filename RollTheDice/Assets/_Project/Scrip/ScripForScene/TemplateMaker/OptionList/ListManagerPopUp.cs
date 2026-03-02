using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;
using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.API.Service.Game.Templates;
using Assets._Project.Scrip.ScripForScene.Bundle;
using System;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ListManagerPopUp : MonoBehaviour
{
    [SerializeField] private Button choosenList;
    [SerializeField] private Button CloseButton;

    public Action<OptionList> OnConfirm;
    public Action OnCancel;

    private OptionList chosenList;
    private TemplateService templateService;
    private void Awake()
    {
        if (choosenList != null)
        {
            choosenList.onClick.AddListener(ChoosenList);
        }
        if (CloseButton != null)
        {
            CloseButton.onClick.AddListener(ClosePopUp);
        }
    }

    private void Start()
    {
        templateService = new TemplateService();
    }

    private void ChoosenList()
    {
       ListManager listManager = FindAnyObjectByType<ListManager>();
       if (listManager != null)
       {
           chosenList = listManager.GetActiveList();
           OnConfirm?.Invoke(chosenList);
           ClosePopUp();
        }
    }
    public void Cancel()
    {
        OnCancel?.Invoke();
        ClosePopUp();
    }
    private void ClosePopUp()
    {
        Destroy(gameObject);
    }

    //TODO : Ajouter une recuperation des Template de la DB
    public void LoadList( )
    {
        List<OptionList> list = new List<OptionList>();
        List<OptionListDTO> listDTO = new List<OptionListDTO>();
         StartCoroutine(

             listDTO = templateService.GetOptionListByIdGameBundle(BundleSession.Instant.Bundle.Id)
        );



        ListManager listManager = FindAnyObjectByType<ListManager>();
        if (listManager != null)
        {
            listManager.LoadListManager(list);
        }
    }
}

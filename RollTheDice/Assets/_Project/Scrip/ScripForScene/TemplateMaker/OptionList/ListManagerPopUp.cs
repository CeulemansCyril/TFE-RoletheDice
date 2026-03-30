using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;
using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.API.Service.Game.Templates;
using Assets._Project.Scrip.ScripForScene.Bundle;
using Assets._Project.Scrip.ScripForScene.Login;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using UnityEngine;
using UnityEngine.UI;

public class ListManagerPopUp : MonoBehaviour
{
    [SerializeField] private Button choosenList;
    [SerializeField] private Button CloseButton;

    public Action<OptionList> OnConfirm;
    public Action OnCancel;

    private ListManager listManager ;

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
        if (listManager == null)
        {
            listManager = FindAnyObjectByType<ListManager>();
        }
    }

    private void Start()
    {
        templateService = new TemplateService();
    }

    private void ChoosenList()
    {
       
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
        OnSaveList();
        Destroy(gameObject);
    }


    public async Task LoadList( )
    {
        List<OptionList> list = new List<OptionList>();

    
        OptionListDTO[] listDTO =  await templateService.GetOptionListByIdGameBundle(BundleSession.Intance.Bundle.Id);

        if (listDTO != null)
        {
            foreach (var dto in listDTO)
            {
                OptionList option = templateService.OptionListDTOToOptionList(dto);
                if (option != null)
                {
                    list.Add(option);
                }
            }
        }

      
        if (listManager != null)
        {
            listManager.LoadListManager(list);
        }
    }

    private void OnSaveList()
    {
        if(listManager != null)
        {
            List<OptionList> listNew = listManager.NewList;
            List<OptionList> listUpdated = listManager.UpdatedList;
            List<OptionList> listDeleted = listManager.RemoveList;
         
            if (listNew != null && listNew.Count > 0)
            {
               
                OptionListDTO[] dtoArray = listNew
                    .Select(o => templateService.OptionListToOptionListDTO(o, 0, BundleSession.Intance.Bundle.Id))
                    .Where(d => d != null)
                    .ToArray();

                if (dtoArray.Length > 1)
                {
                    templateService.CreateManyOption (UserSession.Intance.UserID, dtoArray).GetAwaiter().GetResult();
                }
                else if (dtoArray.Length == 1)
                {
                    templateService.CreateOptionList (dtoArray[0], UserSession.Intance.UserID).GetAwaiter().GetResult();
                }
            }


            if (listUpdated != null && listUpdated.Count > 0)
            {

                OptionListDTO[] dtoArray = listUpdated
                    .Select(o => templateService.OptionListToOptionListDTO(o, 0, BundleSession.Intance.Bundle.Id))
                    .Where(d => d != null)
                    .ToArray();

                if (dtoArray.Length > 1)
                {
                    templateService.UpdateManyOption ( dtoArray).GetAwaiter().GetResult();
                }
                else if (dtoArray.Length == 1)
                {
                    templateService.UpdateOptionList (dtoArray[0]).GetAwaiter().GetResult();
                }
            }



            if (listDeleted != null && listDeleted.Count > 0)
            {

                long[] dtoArray = listDeleted
                    .Select(o => o.Id)
                    .ToArray();

                if (dtoArray.Length > 1)
                {
                    templateService.DeleteManyOptionList(  dtoArray).GetAwaiter().GetResult();
                }
                else if (dtoArray.Length == 1)
                {
                    templateService.DeleteOptionList(dtoArray[0]).GetAwaiter().GetResult();
                }
            }

            listManager.NewList = new List<OptionList>();
                listManager.UpdatedList = new List<OptionList>();
                listManager.RemoveList = new List<OptionList>();
        }
    }
}

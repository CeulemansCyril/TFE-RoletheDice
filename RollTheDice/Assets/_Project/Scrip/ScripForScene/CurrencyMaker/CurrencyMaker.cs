using Assets._Project.API.Model.DTO.GameDTO.MoneyDTO;
using Assets._Project.API.Model.Object.Game.Money;
using Assets._Project.API.Service.Game.Money;
using Assets._Project.Localization;
using Assets._Project.Scrip.Scene;
using Assets._Project.Scrip.ScripForScene.Bundle;
using Assets._Project.Scrip.ScripForScene.Login;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.CurrencyMaker
{
    class CurrencyMaker : MonoBehaviour
    {
        [SerializeField] private GameObject PrefabCurrentItem;
        [SerializeField] private Button AddRow;
        [SerializeField] private Button SaveBut;
        [SerializeField] private Button CloseBut;
        [SerializeField] private Table table;

        private static long currencyID = -1;
        private List<Currency> currencies = new List<Currency>();

        private bool IsSave=true;

        private CurrencyService currencyService;

        private  void Start()
        {
            AddRow.onClick.AddListener(AddNewRow);
            SaveBut.onClick.AddListener(OnSaveButtonClicked);
            CloseBut.onClick.AddListener(OnCloseButton);
            currencyService = new CurrencyService();
            LoadCurrency();
        }

        private async void LoadCurrency()
        {
            CurrencyDTO[] listDTO = await currencyService.GetAllCurrenciesByGameBundleId(BundleSession.Intance.Bundle.Id);
            Currency[] list= new Currency[listDTO.Length];

            int x = listDTO.Length;
            Debug.Log("x : " + x);

            for (int i = 0; i < x; i++)
            {
                list[i] = currencyService.CurrencyDTOToCurrency(listDTO[i]);
            }

            LoadList(list);
        }

        private void AddNewRow()
        {
            GameObject row = table.AddRow(PrefabCurrentItem);

            CurrencyItem item = row.GetComponent<CurrencyItem>();
            item.actionDel += RemoveRow;
            item.Init(new Currency(currencyID, "New Currency", "$", "CUR", 1));
            currencyID--;
        }

        private void LoadList(Currency[] list)
        {
            table.Clear();
            currencies.Clear();
            foreach (Currency item in list) {
                GameObject row = table.AddRow(PrefabCurrentItem);

                CurrencyItem itemCurency = row.GetComponent<CurrencyItem>();
                itemCurency.actionDel += RemoveRow;
                itemCurency.Init(item);
                currencies.Add(item);
            }
        }

        private void RemoveRow(CurrencyItem currencyItem)
        {
            Currency currency = currencyItem.GetCurrency();

            if (currency == null) return;

            currencies.Remove(currency);

            currencyService.DeleteCurrency(currency.Id);
        }

        private async void SaveCurrency()
        {
           CurrencyItem[] currencyItems = table.GetRows<CurrencyItem> ();
           CurrencyDTO[] currencyDTOs = new CurrencyDTO[currencyItems.Length];
           int x = currencyItems.Length;

            for (int i = 0; i < x; i++)
            {
                Currency currency = currencyItems[i].GetCurrency();
                currencyDTOs[i] = currencyService.CurrencyToCurrencyDTO(currency,BundleSession.Intance.Bundle.Id);
            }

            Currency[] currencies = await currencyService.SaveAllCurrencies(currencyDTOs,UserSession.Intance.UserID);

            LoadList(currencies);


        }

        private void OnClose()
        {
            SceneLoader.Instance.LoadScene(Scene.Scene.MainMenuCreate);
        }

        private void OnSaveButtonClicked()
        {
            SaveBut.interactable = false;
            SaveCurrency();
  
            SaveBut.interactable = true;
        }

        private void OnCloseButton()
        {
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
            SaveCurrency();
            OnClose();
        }

        private void OnConfirmCloseWithoutSave()
        {
            OnClose();
        }

    }
}

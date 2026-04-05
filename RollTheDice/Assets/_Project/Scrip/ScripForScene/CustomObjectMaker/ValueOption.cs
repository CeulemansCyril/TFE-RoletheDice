using Assets._Project.API.Model.DTO.GameDTO.MoneyDTO;
using Assets._Project.API.Model.Object.Game.Money;
using Assets._Project.API.Service.Game.Money;
using Assets._Project.Scrip.ScripForScene.Bundle;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TMPro;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.UIElements;

namespace Assets._Project.Scrip.ScripForScene.CustomObjectMaker
{
    public class ValueOption : MonoBehaviour
    {

        [SerializeField] private TMP_InputField field;
        [SerializeField] private TMP_Dropdown dropdownField;

        private CurrencyService currencyService;
        private CurrencyDTO[] ListcurrencyDTOs;

       

        private void Start()
        {
            currencyService = new CurrencyService();
            dropdownField.ClearOptions();

            LoadCombobox();

            field.onValueChanged.AddListener(CheckInputFieldValue);


        }

        private async void LoadCombobox()
        {
            try
            {
                if (BundleSession.Intance == null || BundleSession.Intance.Bundle == null)
                {
                    Debug.LogWarning("BundleSession or Bundle is null in LoadCombobox");
                    return;
                }

                CurrencyDTO[] currencyDTOs = await currencyService.GetAllCurrenciesByGameBundleId(BundleSession.Intance.Bundle.Id);
                ListcurrencyDTOs = currencyDTOs;
                if (currencyDTOs == null) return;
                int x = currencyDTOs.Length;

                List<string> list = new List<string>();
                list.Add("------");

                for (int i = 0; i < x; i++)
                {
                    list.Add(currencyDTOs[i].Name);
                }

                dropdownField.AddOptions(list);
            }
            catch (Exception ex)
            {
                Debug.LogWarning("Failed to load currencies for dropdown: " + ex.Message);
            }
        }

        private void CheckInputFieldValue(string value)
        {
            if(string.IsNullOrEmpty(value)) return;

            string digitsOnly = new string(value.Where(char.IsDigit).ToArray());
            field.text = digitsOnly;

        }


        public Value GetValue()
        {
            Value value = new Value();

            if (ListcurrencyDTOs == null || ListcurrencyDTOs.Length == 0) return value;
 
            int index = dropdownField.value;

             
            int dtoIndex = index - 1;
            if (dtoIndex >= 0 && dtoIndex < ListcurrencyDTOs.Length)
            {
                CurrencyDTO currencyDTO = ListcurrencyDTOs[dtoIndex];
                
                value.Currency = currencyService.CurrencyDTOToCurrency(currencyDTO);
                value.Amount =int.Parse( field.text);
            }

            return value;
        }

        public async Task SetValue(Value value)
        {
            if (field == null || dropdownField == null)
            {
                return;
            }

            if (value == null)
            {
                field.text = "";
                dropdownField.value = 0;
                dropdownField.RefreshShownValue();
                return;
            }

            field.text = value.Amount.ToString(System.Globalization.CultureInfo.InvariantCulture);
               

            if (value.Currency != null)
            {
               
                if (currencyService == null)
                    currencyService = new CurrencyService();

                
                CurrencyDTO currencyDTO = null;
                if (ListcurrencyDTOs != null && ListcurrencyDTOs.Length > 0)
                {
                    currencyDTO = Array.Find(ListcurrencyDTOs, d => d.Id == value.Currency.Id);
                }

                if (currencyDTO == null)
                {
        
                    currencyDTO = await currencyService.GetCurrencyById(value.Currency.Id);
                }

                Currency currency = currencyService.CurrencyDTOToCurrency(currencyDTO);
                Debug.Log("Currency Name: " + currency.Name + " Currency id : " + currency.Id);

                int foundIndex = -1;

         
                if (dropdownField.options == null || dropdownField.options.Count == 0)
                {
                    if (ListcurrencyDTOs == null || ListcurrencyDTOs.Length == 0)
                    {
                        try
                        {
                            ListcurrencyDTOs = await currencyService.GetAllCurrenciesByGameBundleId(BundleSession.Intance.Bundle.Id);
                        }
                        catch (Exception ex)
                        {
                            Debug.LogWarning("Failed to load currencies for dropdown: " + ex.Message);
                        }
                    }

                    if (ListcurrencyDTOs != null && ListcurrencyDTOs.Length > 0)
                    {
                        List<string> list = new List<string> { "------" };
                        for (int i = 0; i < ListcurrencyDTOs.Length; i++)
                            list.Add(ListcurrencyDTOs[i].Name ?? string.Empty);

                        dropdownField.ClearOptions();
                        dropdownField.AddOptions(list);
                    }
                }

    
                if (dropdownField.options != null && dropdownField.options.Count > 0)
                {
                    string target = (currency.Name ?? string.Empty).Trim();
                    foundIndex = dropdownField.options
                        .FindIndex(o => (o.text ?? string.Empty).Trim().Equals(target, StringComparison.OrdinalIgnoreCase));

                
                    for (int i = 0; i < dropdownField.options.Count; i++)
                    {
                        Debug.Log($"Dropdown option [{i}] = '" + (dropdownField.options[i].text ?? string.Empty) + "'");
                    }
                    Debug.Log($"Searching for target '{target}' foundIndex={foundIndex}");
                }
                else if (ListcurrencyDTOs != null && ListcurrencyDTOs.Length > 0)
                {
                    int dtoIdx = Array.FindIndex(ListcurrencyDTOs, d => string.Equals(d.Name, currency.Name, StringComparison.OrdinalIgnoreCase));
                    if (dtoIdx >= 0)
                        foundIndex = dtoIdx + 1; 
                }

                dropdownField.value = foundIndex >= 0 ? foundIndex : 0;
            }
            else
            {
                dropdownField.value = 0;
            }

            dropdownField.RefreshShownValue();
        }


    }
}
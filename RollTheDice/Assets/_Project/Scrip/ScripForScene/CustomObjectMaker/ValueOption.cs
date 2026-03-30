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
            CurrencyDTO[] currencyDTOs = await currencyService.GetAllCurrenciesByGameBundleId(BundleSession.Intance.Bundle.Id);
            ListcurrencyDTOs = currencyDTOs;
            int x = currencyDTOs.Length;

            List<string> list = new List<string>();
            list.Add("------");

            for (int i = 0; i < x; i++)
            {
                list.Add(currencyDTOs[i].Name);
            }

            dropdownField.AddOptions(list);

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

        public void SetValue(Value value)
        {
            if (value == null)
            {
                field.text = "";
                dropdownField.value = 0;
                dropdownField.RefreshShownValue();
                return;
            }

            field.text = value.Amount.ToString();

            if (value.Currency != null && dropdownField.options != null && dropdownField.options.Count > 0)
            {
                int foundIndex = dropdownField.options
                    .FindIndex(o => o.text.Equals(value.Currency.Name, StringComparison.OrdinalIgnoreCase));

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
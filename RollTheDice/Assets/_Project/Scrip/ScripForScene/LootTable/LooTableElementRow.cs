using Assets._Project.API.Enums;
using Assets._Project.API.Model.DTO.GameDTO.LootTableDTO;
using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;
using Assets._Project.API.Model.Object.Game.Money;
using Assets._Project.API.Service.Game.Templates;
using Assets._Project.Scrip.ScripForScene.Bundle;
using Assets._Project.Scrip.ScripForScene.CustomObjectMaker;
using Assets._Project.Scrip.ScripUI.RenameField;
using Assets._Project.Scrip.ScripUI.SearchableDropdown;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.LootTable
{
    public class LooTableElementRow: MonoBehaviour, IPointerClickHandler
    {
        [SerializeField] private TMP_Dropdown dropdown;

        [SerializeField] private RenameField MinAmount;
        [SerializeField] private RenameField MaxAmount;
        [SerializeField] private RenameField Weight;
        [SerializeField] private RenameField DropChance;

        [SerializeField] private ValueOption valueOption;
        [SerializeField] private SearchableDropdown listCustomObject;

        [Header("Selection")]
        [SerializeField] private Image backgroundImage;
        [SerializeField] private Color normalColor = new Color(1f, 1f, 1f, 0f);
        [SerializeField] private Color selectedColor = new Color(0.8f, 0.9f, 1f, 1f);

        public event Action<LooTableElementRow> OnRowClicked;

        private LootElementDTO LootElement;
        private TemplateService templateService;
        private CustomObjectDTO[] customObjects;

        private long id = -1;

        public void Init(LootElementDTO loot, long id)
        {
            LootElement = loot;
            this.id = id;
            Debug.Log("loot element id drop object: " + LootElement.IdDropObject);
            templateService = new TemplateService();

            MinAmount.EndRename +=     () => EndRenameBasicValue(MinAmount); 
            MaxAmount.EndRename +=     () => EndRenameBasicValue(MaxAmount); 
            Weight.EndRename +=        () => EndRenameBasicValue(Weight); 
            DropChance.EndRename +=    () => EndRenameBasicValue(DropChance);

            dropdown.onValueChanged.AddListener(OnTypeChanged);

            LoadList();
            LoadListCustomObject();
            OnTypeChanged(dropdown.value);
        }

        private void LoadList()
        {
            dropdown.ClearOptions();

            dropdown.AddOptions(new List<string> { LootType.OBJECT.ToString(), LootType.CURRENCY.ToString() });
            if (LootElement != null)
            {
                MaxAmount.SetText(LootElement.MaxAmount.ToString());
                MinAmount.SetText(LootElement.MinAmount.ToString());
                Weight.SetText(LootElement.Weight.ToString());
                DropChance.SetText(LootElement.DropChance.ToString());
            }
            else
            {
                MaxAmount.SetText("0");
                MinAmount.SetText("0");
                Weight.SetText("0");
                DropChance.SetText("0");
            }
        }


        private void EndRenameBasicValue(RenameField baseValue)
        {
            string txt = baseValue.GetTitle();

            if (!string.IsNullOrEmpty(txt))
            {
                string digitsOnly = new string(txt.Where(char.IsDigit).ToArray());

                if (!string.IsNullOrEmpty(digitsOnly))
                {
                    baseValue.SetText(int.Parse(digitsOnly).ToString());
                }
                else
                {
                    baseValue.SetText("0");
                }
            }
            else
            {
                baseValue.SetText("0");
            }
        }

        public LootElementDTO GetLootElement()
        {
            LootElement.MinAmount = int.Parse(MinAmount.GetTitle());
            LootElement.MaxAmount = int.Parse(MaxAmount.GetTitle());
            LootElement.Weight = int.Parse(Weight.GetTitle());
            LootElement.DropChance = double.Parse(DropChance.GetTitle());
            LootElement.Type = (LootType)dropdown.value;

            if (LootElement.Type == LootType.OBJECT)
            {
                int selectedIndex = listCustomObject.GetSelectedOriginalIndex();
                if (customObjects != null && selectedIndex >= 0 && selectedIndex < customObjects.Length)
                {
                    LootElement.IdDropObject = customObjects[selectedIndex].Id;
                }
            }
            else
            {
                Value price = valueOption.GetValue();
                LootElement.Value = price;
            }

            return LootElement;
        }

        private async void LoadListCustomObject()
        {
            if (BundleSession.Intance == null || BundleSession.Intance.Bundle == null) return;

            try
            {
                customObjects = await templateService.GetAllCustomObjectByGameBundleId(BundleSession.Intance.Bundle.Id);
            }
            catch (System.Exception ex)
            {
                Debug.LogError("Error loading custom objects: " + ex.Message);
                customObjects = null;
            }

            if (customObjects == null || customObjects.Length == 0)
            {
                listCustomObject.ClearOptions();
                return;
            }

            List<string> names = new List<string>();
            for (int i = 0; i < customObjects.Length; i++)
            {
                names.Add(customObjects[i].Name ?? "???");
            }

            listCustomObject.SetOptions(names);

            if (LootElement != null && LootElement.IdDropObject > 0)
            {
                for (int i = 0; i < customObjects.Length; i++)
                {
                    if (customObjects[i].Id == LootElement.IdDropObject)
                    {
                        listCustomObject.SetValue(i);
                        break;
                    }
                }
            }
        }

        private void OnTypeChanged(int index)
        {
            LootType selectedType = (LootType)index;

            bool isObject = selectedType == LootType.OBJECT;

            listCustomObject.gameObject.SetActive(isObject);
            valueOption.gameObject.SetActive(!isObject);
        }

        public void OnPointerClick(PointerEventData eventData)
        {
            OnRowClicked?.Invoke(this);
        }

        public long GetId()
        {
            return id;
        }

        public void SetSelected(bool selected)
        {
            if (backgroundImage != null)
            {
                backgroundImage.color = selected ? selectedColor : normalColor;
            }
        }
    }
}
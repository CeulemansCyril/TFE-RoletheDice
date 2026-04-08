using Assets._Project.API.Enums;
using Assets._Project.API.Model.DTO.GameDTO.LootTableDTO;
using Assets._Project.API.Model.Object.Game.Money;
using Assets._Project.Scrip.ScripForScene.CustomObjectMaker;
using Assets._Project.Scrip.ScripUI.RenameField;
using Assets._Project.Scrip.ScripUI.SearchableDropdown;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using TMPro;
using UnityEngine;

namespace Assets._Project.Scrip.ScripForScene.LootTable
{
    public class LooTableElementRow: MonoBehaviour
    {
        [SerializeField] private TMP_Dropdown dropdown;

        [SerializeField] private RenameField MinAmount;
        [SerializeField] private RenameField MaxAmount;
        [SerializeField] private RenameField Weight;
        [SerializeField] private RenameField DropChance;

        [SerializeField] private ValueOption valueOption;
        [SerializeField] private SearchableDropdown listCustomObject;
        


        private LootElementDTO LootElement;

        public void Init(LootElementDTO loot)
        {
            LootElement = loot;
            
            MinAmount.EndRename +=     () => EndRenameBasicValue(MinAmount); 
            MaxAmount.EndRename +=     () => EndRenameBasicValue(MaxAmount); 
            Weight.EndRename +=        () => EndRenameBasicValue(Weight); 
            DropChance.EndRename +=    () => EndRenameBasicValue(DropChance);
            

            LoadList(); 
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
            LootElement.MinAmount = int.Parse(MaxAmount.GetTitle());
            LootElement.Weight = int.Parse(Weight.GetTitle());
            LootElement.DropChance = double.Parse(DropChance.GetTitle());
            LootElement.Type = (LootType)dropdown.value;

            return LootElement;
        }

        private void LoadListCustomObject()
        {

        }
    }
}
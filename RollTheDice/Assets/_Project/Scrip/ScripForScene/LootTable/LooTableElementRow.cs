using Assets._Project.API.Enums;
using Assets._Project.API.Model.DTO.GameDTO.LootTableDTO;
using Assets._Project.API.Model.Object.Game.Money;
using Assets._Project.Scrip.ScripUI.RenameField;
using System.Collections;
using System.Collections.Generic;
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


        private LootElementDTO LootElement;

        public void Init(LootElementDTO loot)
        {
            LootElement = loot;
            
            MinAmount.OnEndEdit.AddListener((string txt) => { EndRenameBasicValue(); });
            MaxAmount.OnEndEdit.AddListener((string txt) => { EndRenameBasicValue(); });
            Weight.OnEndEdit.AddListener((string txt) => { EndRenameBasicValue(); });
            DropChance.OnEndEdit.AddListener((string txt) => { EndRenameBasicValue(); });


            LoadList(); 
        }

        private void LoadList()
        {
            dropdown.ClearOptions();


            dropdown.AddOptions(new List<string> { LootType.OBJECT.ToString(), LootType.CURRENCY.ToString() });
            if (LootElement.id != null)
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


        private void EndRenameBasicValue()
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


    }
}
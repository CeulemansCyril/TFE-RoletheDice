using Assets._Project.API.Enums;
using Assets._Project.API.Model.DTO.GameDTO.LootTableDTO;
using Assets._Project.Scrip.ScripUI.RenameField;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

namespace Assets._Project.Scrip.ScripForScene.LootTable
{
    public class LooTableRow : MonoBehaviour
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
        }

        private void LoadList()
        {
            dropdown.ClearOptions();

             
            dropdown.AddOptions(new List<string> { LootType.OBJECT.ToString(),LootType.CURRENCY.ToString() });
        }
    }
}
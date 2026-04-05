using UnityEngine;
using System.Collections;
using UnityEngine.EventSystems;
using Assets._Project.Scrip.ScripUI.RenameField;
using Assets._Project.API.Model.Object.Game.LootTable;
using System;

namespace Assets._Project.Scrip.ScripForScene.LootTable
{
	public class LootTableRow: MonoBehaviour, IPointerClickHandler
    {
		[SerializeField] private RenameField label;

        private float lastClickTime = 0;

        private LootTables lootTable;
		public Action<LootTables> click;
        private bool isRenaming = false;

        public void Init(LootTables lootTable)
		{
			this.lootTable = lootTable;

			label.SetText(lootTable.Name);
 
            label.EndRename += ()=> OnEndEdit();
        }

        public void OnPointerClick(PointerEventData eventData)
        {
            if (isRenaming) return;


            if (eventData.clickCount == 2)
            {

                lastClickTime = 0f;
                ClickRename();
            }
            else
            {
                ClickRename();
                lastClickTime = Time.time;
            }
        }


        private void ClickRename()
		{
			click?.Invoke(lootTable);
        }

        private void OnEndEdit( )
        {
            string newName = label.GetTitle();
            if (string.IsNullOrEmpty(newName))
            {
                label.SetText(lootTable.Name);
            }
            else
            {
                lootTable.Name = newName;
                label.SetText(newName);
            }
 
        }

       


    }
}
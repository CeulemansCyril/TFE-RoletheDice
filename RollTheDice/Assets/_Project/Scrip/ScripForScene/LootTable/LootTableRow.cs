using UnityEngine;
using UnityEngine.EventSystems;
using Assets._Project.Scrip.ScripUI.RenameField;
using Assets._Project.API.Model.Object.Game.LootTable;
using System;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.LootTable
{
	public class LootTableRow: MonoBehaviour, IPointerClickHandler
    {
		[SerializeField] private RenameField label;

		[Header("Selection")]
		[SerializeField] private Image backgroundImage;
		[SerializeField] private Color normalColor = new Color(1f, 1f, 1f, 0f);
		[SerializeField] private Color selectedColor = new Color(0.8f, 0.9f, 1f, 1f);

		private LootTables lootTable;
		public Action<LootTables> click;
		public event Action<LootTableRow> OnRowClicked;
		private bool isRenaming = false;

        public void Init(LootTables lootTable)
		{
			this.lootTable = lootTable;
 

            label.SetText(lootTable.Name);
			label.SetClickable(false);

			label.EndRename += () => OnEndEdit();
        }

		public void OnPointerClick(PointerEventData eventData)
		{
			if (isRenaming) return;

			if (eventData.clickCount == 2)
			{
			 
                isRenaming = true;
				label.StartRename();
			}
			else if (eventData.clickCount == 1)
			{
			 
                Select();
			}
		}

		private void Select()
		{
			click?.Invoke(lootTable);
			OnRowClicked?.Invoke(this);
		}

		private void OnEndEdit()
		{
			isRenaming = false;

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

        public void SetSelected(bool selected)
        {
            if (backgroundImage != null)
            {
                backgroundImage.color = selected ? selectedColor : normalColor;
            }
        }

        public LootTables GetLootTable()
        {
            return lootTable;
        }

    }
}
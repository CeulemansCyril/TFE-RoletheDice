using UnityEngine;
using System.Collections;

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
			label.onClick.AddListener(OnPointerClick);
            label.onEndEdit.AddListener(OnEndEdit);
        }

        public void OnPointerClick(PointerEventData eventData)
        {
            if (isRenaming) return;


            if (eventData.clickCount == 2)
            {

                lastClickTime = 0f;
                StarRename();
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

        private void OnEndEdit(string newName)
        {
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
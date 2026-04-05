using Assets._Project.API.Model.DTO.GameDTO.LootTableDTO;
using Assets._Project.API.Model.Object.Game.LootTable;
using Assets._Project.API.Service.Game.LootTable;
using Assets._Project.Localization;
using Assets._Project.Scrip.ScripForScene.Login;
using UnityEngine;
using UnityEngine.UI;
namespace Assets._Project.Scrip.ScripForScene.LootTable
{
    class LootTableMaker : MonoBehaviour
    {
        [SerializeField] private Button AddRow;
        [SerializeField] private Button SaveBut;
        [SerializeField] private Button CloseBut;

        [SerializeField] private LooTableElementRow rowPrefab;

        [SerializeField] private Transform listLootTable;
        [SerializeField] private LootTableRow lootTableRowPrefab;

        [SerializeField] private Table table;

        private LootTables[] lootTable;
        private LootTables currentLootTable;

        private LootTableRow currentTableRowPrefab;

        private bool IsSave = true;

        private LootTableService lootTableService ;

        public void Init(LootTables[] lootTable)
        {
            this.lootTable = lootTable;

            AddRow.onClick.AddListener(AddNewRow);
            SaveBut.onClick.AddListener(OnSaveButtonClicked);
            CloseBut.onClick.AddListener(OnCloseButton);

            LoadListTable();

        }

        private void LoadListTable()
        {
            foreach (LootTables loot in lootTable)
            {
                GameObject row = table.AddRow(lootTableRowPrefab.gameObject);
                LootTableRow item = row.GetComponent<LootTableRow>();
                item.Init(loot);
                item.click += OnClickedRow;
            }
        }


        private void OnClickedRow(LootTables lootTable)
        {
            if (currentLootTable != null && currentLootTable.Id != lootTable.Id)
            {
                SaveLootTable();

            }


            table.Clear();

            LoadList(lootTable.LootElements);
        }



        private void LoadList(LootElementDTO[] list)
        {
            foreach (LootElementDTO loot in list)
            {
                GameObject row = table.AddRow(rowPrefab.gameObject);
                LooTableElementRow item = row.GetComponent<LooTableElementRow>();
                item.Init(loot);
            }
        }

        private void AddNewRow()
        {
            GameObject row = table.AddRow(rowPrefab.gameObject);
            LooTableElementRow item = row.GetComponent<LooTableElementRow>();
            item.Init(new LootElementDTO());
        }

        private async void SaveLootTable()
        {
            LooTableElementRow[] list = table.GetRows<LooTableElementRow>();

            currentLootTable.LootElements = new LootElementDTO[list.Length];

            for (int i = 0; i < list.Length; i++)
            {
                currentLootTable.LootElements[i] = list[i].GetLootElement();
            }

            LootTableDTO lootTable = new LootTableDTO();
            lootTable = lootTableService.LootTableToLootTableDTO(currentLootTable);

            if (currentLootTable.Id == 0)
            {
                lootTable = await lootTableService.CreateLootTable(lootTable, UserSession.Intance.UserID );
                currentLootTable.Id = lootTable.Id;
            }
            else
            {
                await lootTableService.UpdateLootTable(lootTable);
            }

        }

        private void OnClose()
        {
            SceneLoader.Instance.LoadScene(Scene.Scene.MainMenuCreate);
        }

        private void OnSaveButtonClicked()
        {
            SaveBut.interactable = false;
            SaveLootTable();

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
            SaveLootTable();
            OnClose();
        }

        private void OnConfirmCloseWithoutSave()
        {
            OnClose();
        }


    }
}

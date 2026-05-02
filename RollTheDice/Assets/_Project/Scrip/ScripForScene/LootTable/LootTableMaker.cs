using Assets._Project.API.Model.DTO.GameDTO.LootTableDTO;
using Assets._Project.API.Model.Object.Game.LootTable;
using Assets._Project.API.Service.Game.LootTable;
using Assets._Project.Localization;
using Assets._Project.Scrip.ScripForScene.Bundle;
using Assets._Project.Scrip.ScripForScene.Login;
using System.Linq;
using System.Threading.Tasks;
using UnityEngine;
using UnityEngine.UI;
namespace Assets._Project.Scrip.ScripForScene.LootTable
{
    class LootTableMaker : MonoBehaviour
    {
        [SerializeField] private Button AddRow;
        [SerializeField] private Button SaveBut;
        [SerializeField] private Button CloseBut;
        [SerializeField] private Button DeleteRow;

        [SerializeField] private Button AddLootTableRowBut;
        [SerializeField] private Button DeleteLootTableRowBut;

        [SerializeField] private LooTableElementRow rowPrefab;

        [SerializeField] private Transform listLootTable;
        [SerializeField] private LootTableRow lootTableRowPrefab;

        [SerializeField] private Table table;

        private LootTables[] lootTable;
        private LootTables currentLootTable;

        private LootTableRow currentTableRowPrefab;
        private LooTableElementRow selectedRow;
        private LootTableRow selectedLootTableRow;
        private long countLootTableId=-1;
        private bool IsSave = true;

        private LootTableService lootTableService ;

         private void Start()
        {
            lootTableService = new LootTableService("");

            AddRow.onClick.AddListener(AddNewRow);
            SaveBut.onClick.AddListener(OnSaveButtonClicked);
            CloseBut.onClick.AddListener(OnCloseButton);
            DeleteRow.onClick.AddListener(OnDeleteRow);

            AddLootTableRowBut.onClick.AddListener(AddNewLootTableRow);
            DeleteLootTableRowBut.onClick.AddListener(OnDeleteLootTableRow);

            LoadAllLootTableAsync();
        }

        private async void LoadAllLootTableAsync()
        {
            LootTableDTO[] lootDTOs = await lootTableService.GetAllLootTableByGameBundleId(BundleSession.Intance.Bundle.Id);

            lootTable = new LootTables[lootDTOs.Length];
            for (int i = 0; i < lootDTOs.Length; i++)
            {
                lootTable[i] = lootTableService.LootTableDTOToLootTable(lootDTOs[i]);
            }

            LoadListTable();
        }
        private void LoadListTable()
        {
            foreach (LootTables loot in lootTable)
            {
                GameObject row = Instantiate(lootTableRowPrefab.gameObject, listLootTable);
                LootTableRow item = row.GetComponent<LootTableRow>();
                item.Init(loot);
                item.click += OnClickedRow;
                item.OnRowClicked += OnLootTableRowClicked;
            }
        }


        private void OnClickedRow(LootTables lootTable)
        {
            if (currentLootTable != null && currentLootTable.Id != lootTable.Id)
            {
                SaveLootTable();
            }else if(currentLootTable != null && currentLootTable.Id == lootTable.Id)
            {
                return;
            }

            currentLootTable = lootTable;

            table.Clear();

            LoadList(lootTable.LootElements);
        }



        private void LoadList(LootElementDTO[] list)
        {
            long count = 0;
            foreach (LootElementDTO loot in list)
            {
                GameObject row = table.AddRow(rowPrefab.gameObject);
                LooTableElementRow item = row.GetComponent<LooTableElementRow>();
                item.Init(loot, count);
                count++;
                item.OnRowClicked += OnElementRowClicked;
            }
        }

        private void AddNewRow()
        {
            GameObject row = table.AddRow(rowPrefab.gameObject);
            LooTableElementRow item = row.GetComponent<LooTableElementRow>();
            long count = table.GetRows<LooTableElementRow>().Length +1;
            item.Init(new LootElementDTO(),count);
            item.OnRowClicked += OnElementRowClicked;
        }

        private void OnElementRowClicked(LooTableElementRow row)
        {
            if (selectedRow != null)
            {
                selectedRow.SetSelected(false);
            }

            selectedRow = row;
            selectedRow.SetSelected(true);
        }

        private void OnDeleteRow()
        {
            if (selectedRow == null) return;
            LootElementDTO lootElementDTO = selectedRow.GetLootElement();
            currentLootTable.LootElements = currentLootTable.LootElements.Where(e => e != lootElementDTO).ToArray();
            Destroy(selectedRow.gameObject);
            lootTableService.UpdateLootTable(lootTableService.LootTableToLootTableDTO(currentLootTable));
            selectedRow = null;
        }

        private void AddNewLootTableRow()
        {
            LootTables newLootTable = new LootTables();
            newLootTable.Name = "New LootTable";
            newLootTable.LootElements = new LootElementDTO[0];
            newLootTable.Id = countLootTableId;
            countLootTableId--;

            GameObject row = Instantiate(lootTableRowPrefab.gameObject, listLootTable);
            LootTableRow item = row.GetComponent<LootTableRow>();
            item.Init(newLootTable);
            item.click += OnClickedRow;
            item.OnRowClicked += OnLootTableRowClicked;
        }

        private void OnLootTableRowClicked(LootTableRow row)
        {
            if (selectedLootTableRow != null)
            {
                LootElementDTO lootElementDTO = selectedRow.GetLootElement();
                long lootTableId = selectedRow.GetId();
                currentLootTable.LootElements[lootTableId] = lootElementDTO;  
                selectedLootTableRow.SetSelected(false);
            }

            selectedLootTableRow = row;
            selectedLootTableRow.SetSelected(true);
        }

        private void OnDeleteLootTableRow()
        {
            if (selectedLootTableRow == null) return;
            lootTableService.DeleteLootTable(selectedLootTableRow.GetLootTable().Id);
            Destroy(selectedLootTableRow.gameObject);
            selectedLootTableRow = null;
        }

        private async void SaveLootTable()
        {
            if (currentLootTable == null) return;

            LooTableElementRow[] list = table.GetRows<LooTableElementRow>();

            currentLootTable.LootElements = new LootElementDTO[list.Length];
            currentLootTable.IdGameBundle = BundleSession.Intance.Bundle.Id;

            for (int i = 0; i < list.Length; i++)
            {
                currentLootTable.LootElements[i] = list[i].GetLootElement();
            }

            LootTableDTO lootTable = new LootTableDTO();
            
            lootTable = lootTableService.LootTableToLootTableDTO(currentLootTable);

            if (currentLootTable.Id <= 0)
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

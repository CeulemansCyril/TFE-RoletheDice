using Assets._Project.API.Model.DTO.GameDTO;
using Assets._Project.API.Service.Game;
using Assets._Project.Scrip.ScripForScene.Bundle;
using System.Collections;
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.Menu.MainMenuCreate
{
	public class MainMenuCreate: MonoBehaviour
	{
		[SerializeField] private TMP_Text title; 
		[SerializeField] private TMP_InputField renameInput;
        [SerializeField] private Button button;

        private string oldName = "";

        private GameBundleService bundleService;
        void Start()
		{
            renameInput.gameObject.SetActive(false);
            title.gameObject.SetActive(true);
            bundleService = new GameBundleService();
            renameInput.onEndEdit.AddListener(EndRename);
            button.onClick.AddListener(() => StartRename());

            title.text = BundleSession.Intance.Bundle.Name;
        }

        public void StartRename()
        {
            oldName = title.text;

            title.gameObject.SetActive(false);

            renameInput.gameObject.SetActive(true);
            renameInput.text = oldName;
            renameInput.Select();
            renameInput.ActivateInputField();

        }

        private void EndRename(string newName)
        {
            if (string.IsNullOrWhiteSpace(newName))
                newName = oldName;



            title.text = newName;

            renameInput.gameObject.SetActive(false);
            title.gameObject.SetActive(true);

            BundleSession.Intance.Bundle.Name = newName;

            GameBundleDTO bundleDTO = bundleService.GameBundleToGameBundleDTO(BundleSession.Intance.Bundle);
            bundleService.UpdateGameBundle(bundleDTO);
        }

        public void OnPointerClick(PointerEventData eventData)
        {
            StartRename();
        }


    }
}
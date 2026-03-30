using UnityEngine;
using System.Collections;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.Menu.MainMenu.BundleManager
{
	public class BundlePopUp: MonoBehaviour
	{
        [SerializeField] private Button closeButton;

 

        public void Show() 
        {
            closeButton.onClick.AddListener(Close);

        }

        private void Close()
        {
            Destroy(gameObject);
        }


    }
}
using UnityEngine;
using System.Collections;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.Menu.MainMenu.BundleManager
{
	public class ButtonBundle: MonoBehaviour
	{
		[SerializeField] private GameObject MainContnent;
		[SerializeField] private GameObject MenuBundlePan;

		[SerializeField] private Button Button;

        private void Start()
		{
			Button.onClick.AddListener(LoadBundle);
        }

		private void LoadBundle()
		{
			MainContnent.SetActive(false);
			BundleItemManager bundleItemManager = MenuBundlePan.GetComponent<BundleItemManager>();
			if (bundleItemManager != null) bundleItemManager.RefreshView();
            MenuBundlePan.SetActive(true);

        }

    }
}
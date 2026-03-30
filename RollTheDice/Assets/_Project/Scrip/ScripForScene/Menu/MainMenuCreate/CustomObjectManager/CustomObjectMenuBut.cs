using UnityEngine;
using System.Collections;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.Menu.MainMenuCreate.CreateCustomObject
{
	public class CustomObjectMenuBut: MonoBehaviour
	{
		[SerializeField] private Button button;
        [SerializeField] private GameObject PerfabMain;
        [SerializeField] private GameObject PerfabCreat;
        [SerializeField] private GameObject Parent;
        void Start()
		{
            button.onClick.AddListener(OnBack);
        }
        private void ClearParent()
        {
            Transform parent = Parent.transform;
            foreach (Transform child in parent)
            {
                Destroy(child.gameObject);
            }
        }

        private void Oncreated()
        {
            ClearParent();

            Transform transform = Parent.transform;

            var obj2 = Instantiate(PerfabCreat, transform);
            CreateCustomObject customObject = obj2.GetComponent<CreateCustomObject>();
            customObject.OnBack += OnBack;

        }

        private void OnBack()
        {
            ClearParent();
            Transform transform = Parent.transform;

            var obj2 = Instantiate(PerfabMain, transform);
            CustomObjectManager customObject = obj2.GetComponent<CustomObjectManager>();
            customObject.ClickOnCreated += Oncreated;
        }

    }
}
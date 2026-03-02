using UnityEngine;
using System.Collections;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.BookMaker
{
	public class ToolbarToggle: MonoBehaviour
	{
		[SerializeField] private Image background;
        [SerializeField] private Color normalColor;
        [SerializeField] private Color activeColor;

        private Toggle toggle;

        private void Awake()
        {
            toggle = GetComponent<Toggle>();
            toggle.onValueChanged.AddListener(OnToggleChanged);
        }

        private void OnToggleChanged(bool isOn)
        {
            background.color = isOn ? activeColor : normalColor;
        }

    }
}
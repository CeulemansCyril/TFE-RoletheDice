using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;
using static UnityEngine.Rendering.CoreUtils;

namespace Assets._Project.Scrip.ScripUI.SwitchPanel
{
	public class SwitchGroup: MonoBehaviour
	{

		[SerializeField] private Transform transform;
		private List<SwitchPanel> switchPanels = new List<SwitchPanel>();
		private SwitchPanel activePan;

		void Start()
		{
            int childCount = transform.childCount;

			for (int i = 0; i < childCount; i++)
			{
				SwitchPanel switchPanel = transform.GetChild(i).GetComponent<SwitchPanel>();
				if (switchPanel != null)
				{
					switchPanel.actionClicked += ClickElement;
                    switchPanels.Add(switchPanel);
				}
			}
			
			LoadPan(0);
        }

		private void LoadPan(int index)
		{
			SwitchPanel pan = switchPanels[index];
			pan.EnableContent(true);
			activePan = pan;
        }

		private void ClickElement(SwitchPanel switchPanel)
		{
			if (activePan != switchPanel)
			{
				if(activePan != null) activePan.EnableContent(false);
				activePan = switchPanel;
				activePan.EnableContent(true);
			}
		}
	}
}
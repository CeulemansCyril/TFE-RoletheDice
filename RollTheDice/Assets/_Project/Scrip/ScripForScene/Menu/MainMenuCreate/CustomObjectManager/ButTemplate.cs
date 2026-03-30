using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using Assets._Project.API.Model.Object.Game.Templates;
using TMPro;
using System;

namespace Assets._Project.Scrip.ScripForScene.CustomObjectMaker
{
	public class ButTemplate: MonoBehaviour
	{
		[SerializeField] public Button button;
		[SerializeField] private TMP_Text label;
        private Template template;
		public Action<ButTemplate> ButCallback;


		public void Init(Template template) 
		{ 
			this.template = template;
			
 
			label.text=template.Name;

			button.onClick.AddListener(OnButClick);

		}

		private void OnButClick()
		{
			ButCallback.Invoke(this);
		}

		public void DestroyThis()
		{
			Destroy(this.gameObject);
		}

		public Template GetTemplate()
		{
			return this.template;
		}

		public void DisableButton()
		{
			button.interactable = false;
		}
	 
	}
}
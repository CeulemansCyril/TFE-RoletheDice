using System;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripUI.SwitchPanel
{
        public class SwitchPanel : MonoBehaviour
        {
            [SerializeField] private Button header; 
            [SerializeField] private Transform container;
            [SerializeField] private Image background;

            [Header("Style")]
            [SerializeField] private Color selectedColor = Color.white;
            [SerializeField] private Color normalColor = Color.gray;

                public Action<SwitchPanel> actionClicked;

            private void Start()
            {
                header.onClick.AddListener(Click);
            }


            private void Click()
            {
                actionClicked?.Invoke(this);
            }

            public void EnableContent(bool enable)
            {
               container.gameObject.SetActive(enable);
               background.color = enable ? selectedColor : normalColor;
             }
    }
}

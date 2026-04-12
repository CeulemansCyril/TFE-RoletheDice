using System;
using System.Collections.Generic;
using System.Text;
using System.Xml.Linq;
using TMPro;
using UnityEngine;

namespace Assets._Project.Scrip.ScripUI.RenameField
{
    class RenameField : MonoBehaviour
    {
        [SerializeField] private TMP_Text titleRename;
        [SerializeField] private Rename rename;

        public Action EndRename;
        public Action Onclick;

        private void Awake()
        {
            rename.endRename += () => EndRename?.Invoke();
        }

        public void SetText(string text)
        {
            titleRename.text = text;
        }


        public string GetTitle()
        {
            return titleRename.text;
        }

        public void SetupRename(Action callback)
        {
            rename.endRename -= callback;
            rename.endRename += callback;
        }

        public void SetupOnClick(Action callback)
        {
            rename.click -= callback;
            rename.click += callback;
        }

        public void StartRename()
        {
            rename.StartRename();
        }

        public void SetClickable(bool clickable)
        {
            titleRename.raycastTarget = clickable;
            rename.SetClickable(clickable);
        }

    }
}

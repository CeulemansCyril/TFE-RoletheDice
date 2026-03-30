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

 
        public void SetText(string text)
        {
            titleRename.text = text;
        }


        public string GetTitle()
        {
            return titleRename.text;
        }

        public void Setup(Action callback)
        {
            rename.endRename -= callback;
            rename.endRename += callback;
        }
    }
}

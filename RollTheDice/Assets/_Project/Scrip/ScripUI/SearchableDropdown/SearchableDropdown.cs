using System;
using System.Collections.Generic;
using System.Linq;
using TMPro;
using UnityEngine;
 

namespace Assets._Project.Scrip.ScripUI.SearchableDropdown
{
    public class SearchableDropdown : MonoBehaviour
    {
        [SerializeField] private TMP_Dropdown dropdown;
        [SerializeField] private TMP_InputField searchField;

        private List<string> allOptions = new List<string>();
        private List<int> filteredIndices = new List<int>();

        public event Action<int> OnSelectionChanged;

        private void Awake()
        {
            searchField.onValueChanged.AddListener(OnSearchTextChanged);
            dropdown.onValueChanged.AddListener(OnDropdownValueChanged);
        }

        public void SetOptions(List<string> options)
        {
            allOptions = new List<string>(options);
            searchField.text = "";
            RefreshDropdown(allOptions);
        }

        public void ClearOptions()
        {
            allOptions.Clear();
            filteredIndices.Clear();
            dropdown.ClearOptions();
            searchField.text = "";
        }

        public int GetSelectedOriginalIndex()
        {
            if (dropdown.value < 0 || dropdown.value >= filteredIndices.Count)
                return -1;

            return filteredIndices[dropdown.value];
        }

        public string GetSelectedOptionText()
        {
            if (dropdown.value < 0 || dropdown.value >= filteredIndices.Count)
                return null;

            return allOptions[filteredIndices[dropdown.value]];
        }

        public void SetValue(int originalIndex)
        {
            int filteredIndex = filteredIndices.IndexOf(originalIndex);
            if (filteredIndex >= 0)
            {
                dropdown.SetValueWithoutNotify(filteredIndex);
            }
        }

        private void OnSearchTextChanged(string searchText)
        {
            if (string.IsNullOrEmpty(searchText))
            {
                RefreshDropdown(allOptions);
                return;
            }

            string lower = searchText.ToLower();
            var filtered = new List<string>();
            filteredIndices.Clear();

            for (int i = 0; i < allOptions.Count; i++)
            {
                if (allOptions[i].ToLower().Contains(lower))
                {
                    filtered.Add(allOptions[i]);
                    filteredIndices.Add(i);
                }
            }

            dropdown.ClearOptions();
            if (filtered.Count > 0)
            {
                dropdown.AddOptions(filtered);
            }
        }

        private void RefreshDropdown(List<string> options)
        {
            filteredIndices.Clear();
            dropdown.ClearOptions();

            for (int i = 0; i < options.Count; i++)
            {
                filteredIndices.Add(i);
            }

            if (options.Count > 0)
            {
                dropdown.AddOptions(options);
            }
        }

        private void OnDropdownValueChanged(int index)
        {
            int originalIndex = GetSelectedOriginalIndex();
            OnSelectionChanged?.Invoke(originalIndex);
        }

        private void OnDestroy()
        {
            searchField.onValueChanged.RemoveListener(OnSearchTextChanged);
            dropdown.onValueChanged.RemoveListener(OnDropdownValueChanged);
        }
    }
}

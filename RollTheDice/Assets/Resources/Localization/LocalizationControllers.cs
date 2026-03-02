using Assets._Project.API.Model.Object.Game.Map;
using Assets._Project.Localization.Localization;
using System;
using System.Collections.Generic;
using System.Text;
using UnityEngine;
using static UnityEngine.InputSystem.HID.HID;

namespace Assets._Project.Localization
{
    public  class LocalizationControllers : MonoBehaviour
    {
        public static LocalizationControllers Instance { get; private set; }

        public LanguageEnum language = LanguageEnum.FR;

        private Dictionary<string, string> localizedText = new();

        public static event Action OnLanguageChanged;

        private void Awake()
        {
            if (Instance != null && Instance != this)
            {
                Destroy(gameObject);
                return;
            }

            Instance = this;
            DontDestroyOnLoad(gameObject);

            LoadLangue(language);
        }


        public void LoadLangue(LanguageEnum language)
        {
            this.language = language;

            TextAsset jsonfile = Resources.Load<TextAsset>($"Localization/Language/{language.Description}");
           


            if (jsonfile == null)
            {
                
                return;
            }

            

            LocalizationData data = JsonUtility.FromJson<LocalizationData>(jsonfile.text);


            localizedText = data.ToDictionary();
        
            OnLanguageChanged?.Invoke();

        }

        public string GetLocalizedValue(string key)
        {
            string result = string.Empty;
     
            if (localizedText.ContainsKey(key))
            {
                result = localizedText[key];
            }
            else
            {
                result = key;
            }
            return result;
        }

    }
}

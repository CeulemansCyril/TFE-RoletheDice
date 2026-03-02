using TMPro;
using UnityEngine;

namespace Assets._Project.Localization
{
    [RequireComponent(typeof(TMP_Text))]
    public class LocalizedText : MonoBehaviour
    {
        [SerializeField] private string key;
        private TMP_Text text;

        private void Awake()
        {
            text = GetComponent<TMP_Text>();
        }

        private void Start()
        {
            if(LocalizationControllers.Instance == null)
            {
                return;
            }
            Refresh();
        }

        private void OnEnable()
        {
            LocalizationControllers.OnLanguageChanged += Refresh;
            Refresh();
        }

        private void OnDisable()
        {
            LocalizationControllers.OnLanguageChanged -= Refresh;
        }

        public void Refresh()
        {
            if (LocalizationControllers.Instance == null)
            {
                return;
            }

            text.text = LocalizationControllers.Instance.GetLocalizedValue(key);
        }

    

    }
}

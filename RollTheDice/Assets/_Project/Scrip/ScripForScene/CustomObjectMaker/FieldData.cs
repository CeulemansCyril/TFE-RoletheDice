using Assets._Project.API.Model.DTO.GameDTO.TemplateDTO;
using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.Localization;
using System;
using System.Linq;
using TMPro;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.UIElements;

namespace Assets._Project.Scrip.ScripForScene.Custom
{
    public class FieldData : MonoBehaviour
    {
        [SerializeField] private TMP_Text titleName;
        [SerializeField] private RectTransform transform;
        [Header("Data")]
        public TemplateField templateField;
        [Header("Inputs")]
        [SerializeField] private TMP_InputField stringInput;
        [SerializeField] private TMP_InputField intInput;
        [SerializeField] private UnityEngine.UI.Toggle boolToggle;
        [SerializeField] private TMP_Dropdown comboDropdown;
        [Header("Background")]
        [SerializeField] private UnityEngine.UI.Image background;
        [SerializeField] private Color errorColor = Color.red;
        [SerializeField] private Color normalColor = Color.white;
        [Header("Error")]
        [SerializeField] private TMP_Text errorText;

        public Action OnValueChanged;
      

        private bool hasBeenEdited = false;

        public void Init(TemplateField templateField, CustomObjectAttributeDTO data = null)
        {
            this.templateField = templateField;


            SetupListeners();
            RefreshUI();
            RefreshTypeUI();
            SetFieldData(data);
        }

        public void RefreshUI()
        {

            titleName.text = templateField.Required ? templateField.Label + " * " : templateField.Label;

            transform.anchoredPosition = new Vector2((float)templateField.PositionX, (float)templateField.PositionY);
        }

        private void RefreshTypeUI()
        {

            if (templateField == null) return;

            stringInput.gameObject.SetActive(false);
            intInput.gameObject.SetActive(false);
            boolToggle.gameObject.SetActive(false);
            comboDropdown.gameObject.SetActive(false);

            switch (templateField.Type)
            {
                case "String":
                    stringInput.gameObject.SetActive(true);
                    break;

                case "Int":
                    intInput.gameObject.SetActive(true);
                    intInput.contentType = TMP_InputField.ContentType.IntegerNumber;

                    break;

                case "Boolean":
                    boolToggle.gameObject.SetActive(true);
                    break;

                case "ComboBox":
                    comboDropdown.gameObject.SetActive(true);
                    RefreshComboOptions();
                    break;
            }
        }

        private void RefreshComboOptions()
        {
            comboDropdown.ClearOptions();

            if (templateField.OptionList == null) return;
            if (templateField.OptionList.Options == null) return;

            comboDropdown.AddOptions(templateField.OptionList.Options);

        }

        private void SetFieldData(CustomObjectAttributeDTO data)
        {
            if (templateField == null) return;



            switch (templateField.Type)
            {
                case "String":
                    stringInput.text = data?.Value ?? string.Empty;
                    break;

                case "Int":
                    intInput.gameObject.SetActive(true);
                    intInput.contentType = TMP_InputField.ContentType.IntegerNumber;
                    intInput.text = data?.Value ?? string.Empty;
                    break;

                case "Boolean":
                    boolToggle.gameObject.SetActive(true);
                    if (data != null && bool.TryParse(data.Value, out var parsedBool))
                        boolToggle.isOn = parsedBool;
                    break;

                case "ComboBox":
                    comboDropdown.gameObject.SetActive(true);
                    RefreshComboOptions();
                    if (data != null && int.TryParse(data.Value, out var idx))
                    {
                        comboDropdown.value = idx;
                    }
                    else if (data != null)
                    {
                        var s = data.Value ?? data.ToString();
                        for (int i = 0; i < comboDropdown.options.Count; i++)
                        {
                            if (comboDropdown.options[i].text == s)
                            {
                                comboDropdown.value = i;
                                break;
                            }
                        }
                    }
                    break;
            }
        }



        private void VerifiedMaxMinValue()
        {
            if (templateField == null || intInput == null) return;

            if (!double.TryParse(intInput.text, out double num))
                return;

            double min = templateField.MinValue;
            double max = templateField.MaxValue;

            if (min == 0 && max == 0)
                return;

            double clamped = Math.Clamp(num, min, max);

            if (num != clamped)
            {
                intInput.SetTextWithoutNotify(clamped.ToString());

            }
        }

        public object GetData()
        {

            if (templateField == null) return null;

            switch (templateField.Type)
            {
                case "String":
                    return stringInput != null ? (object)stringInput.text : null;

                case "Int":
                    if (intInput != null && int.TryParse(intInput.text, out var intNumber))
                        return intNumber;
                    return null;

                case "Boolean":

                    return boolToggle != null ? (object)boolToggle.isOn : null;

                case "ComboBox":

                    return comboDropdown != null ? (object)comboDropdown.value : null;

                default:
                    return null;
            }
        }

        public bool IsValid()
        {
            if (templateField == null) return true;

            bool valid = true;
            string error = "";

            if (templateField.Required)
            {
                switch (templateField.Type)
                {
                    case "String":
                        if (string.IsNullOrWhiteSpace(stringInput?.text))
                        {
                            valid = false;
                            error = LocalizationControllers.Instance.GetLocalizedValue("FieldData.Error.Required");
                        }
                        break;

                    case "Int":
                        if (string.IsNullOrWhiteSpace(intInput?.text))
                        {
                            valid = false;
                            error = LocalizationControllers.Instance.GetLocalizedValue("FieldData.Error.Required");
                        }
                        else if (!int.TryParse(intInput.text, out int value))
                        {
                            valid = false;
                            error = LocalizationControllers.Instance.GetLocalizedValue("FieldData.Error.Number");
                        }
                        else if (!(templateField.MinValue == 0 && templateField.MaxValue == 0) &&
                             (value < templateField.MinValue || value > templateField.MaxValue))
                        {
                            valid = false;
                            error = LocalizationControllers.Instance.GetLocalizedValue("FieldData.Error.Number") + templateField.MinValue + " - " + templateField.MaxValue;
                        }
                        break;

                    case "ComboBox":
                        if (comboDropdown == null || comboDropdown.options.Count == 0)
                        {
                            valid = false;
                            error = LocalizationControllers.Instance.GetLocalizedValue("FieldData.Error.Option");
                        }
                        break;
                }
            }


            if (errorText != null)
            {
                errorText.text = error;
                errorText.gameObject.SetActive(!valid && hasBeenEdited);
            }

            background.color = (!valid && hasBeenEdited) ? errorColor : normalColor;

            return valid;
        }
        private void EndRenameBasicValue()
        {
            if (intInput == null || templateField == null)
                return;

            string txt = intInput.text;

            double min = templateField.MinValue;
            double max = templateField.MaxValue;


            double value = min;

            if (!string.IsNullOrWhiteSpace(txt))
            {

                string cleaned = new string(txt.Where(c => char.IsDigit(c) || c == '-').ToArray());


                if (cleaned.Count(c => c == '-') > 1)
                    cleaned = cleaned.Replace("-", "");

                if (double.TryParse(cleaned, out double parsed))
                {
                    value = parsed;
                }
            }

            if (!(min == 0 && max == 0))
            {
                value = Math.Clamp(value, min, max);
            }

     

            intInput.SetTextWithoutNotify(value.ToString());
        }
        private void SetupListeners()
        {


            switch (templateField.Type)
            {
                case "String":
                    stringInput.onValueChanged.AddListener(_ => NotifyChange());
                    return;

                case "Int":
                    intInput.onValueChanged.AddListener(_ => NotifyChange());
                    intInput.onEndEdit.AddListener(_ => VerifiedMaxMinValue());
                    intInput.onEndEdit.AddListener(_ => EndRenameBasicValue());
                    return;

                case "ComboBox":
                    comboDropdown.onValueChanged.AddListener(_ => NotifyChange());
                    return;
                case "Boolean":
                    boolToggle.onValueChanged.AddListener(_ => NotifyChange());
                    return;
            }
        }

        private void NotifyChange()
        {
            hasBeenEdited = true;
            IsValid();
            OnValueChanged?.Invoke();
        }

        public void ResetColor()
        {
            background.color = normalColor;
        }
    }

}
using Assets._Project.API.Enums;
using Assets._Project.API.Model.Object.Game.Templates;
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

public class FieldUI: MonoBehaviour, IPointerClickHandler
{
    [Header("UI")]
    public TMP_Text labelText;
    public RectTransform rectTransform;

    [Header("Data")]
    public TemplateField templateField;

    [Header("Inputs")]
    [SerializeField] private TMP_InputField stringInput;
    [SerializeField] private TMP_InputField intInput;
    [SerializeField] private Toggle boolToggle;
    [SerializeField] private TMP_Dropdown comboDropdown;

    public void Awake()
    {
        if(rectTransform == null) rectTransform = GetComponent<RectTransform>();
    }

    public void Initialize(TemplateField field)
    {
        
        templateField = field;

        RefreshUI();
        RefreshTypeUI();
    }

    public void RefreshUI() { 
        labelText.text = templateField.Label;
   
        rectTransform.anchoredPosition = new Vector2((float)templateField.PositionX, (float)templateField.PositionY);
    }

    public void UpdateSize(float weigth, float heigth) { 
        rectTransform.sizeDelta = new Vector2(weigth, heigth);
    }
    
    public void UpdatePosition (float x, float y) { 
        rectTransform.anchoredPosition = new Vector2(x, y);
    }

    public void UpdateLabel (string newLabel) { 
        labelText.text = newLabel;
    }

    public TemplateField GetTemplateField() {
        templateField.PositionX = rectTransform.anchoredPosition.x;
        templateField.PositionY = rectTransform.anchoredPosition.y;
        templateField.Label = labelText.text;
    
        return templateField;
    }

    public void OnDataChange(string data, string fieldName,OptionList optionList)
    {
        switch (fieldName)
        {
            case "label":
                templateField.Label = data;
                UpdateLabel(data);
                break;
            case "min":
                if (int.TryParse(data, out int minValue))
                {
                    templateField.MinValue = minValue;
                }
                break;
            case "max":
                if (int.TryParse(data, out int maxValue))
                {
                    templateField.MaxValue = maxValue;
                }
                break;
            case "width":
                if (float.TryParse(data, out float widthValue))
                {
                    templateField.Width = widthValue;
                    UpdateSize(widthValue, rectTransform.sizeDelta.x);
                }
                break;
            case "height":
                if (float.TryParse(data, out float heightValue))
                {
                    templateField.Height = heightValue;
                    UpdateSize(rectTransform.sizeDelta.y, heightValue);
                }
                break;
            case "positionX":
                if (float.TryParse(data, out float posXValue))
                {
                    templateField.PositionX = posXValue;
                    UpdatePosition(posXValue, rectTransform.anchoredPosition.y);
                }
                break;
            case "positionY":
                if (float.TryParse(data, out float posYValue))
                {
                    templateField.PositionY = posYValue;
                    UpdatePosition(rectTransform.anchoredPosition.x, posYValue);
                }
                break;
            case "required":
                if (bool.TryParse(data, out bool requiredValue))
                {
                    templateField.Required = requiredValue;
                }
                break;
            case "type":
                templateField.Type = data;
                RefreshTypeUI();
                break;
            case "optionList":
                if (optionList == null) {
                    templateField.OptionList = null;
                    break;
                }
            
                templateField.OptionList = optionList;
                RefreshComboOptions();
                break;

        }
    }

    public void OnPointerClick(PointerEventData eventData)
    {
        FieldSelectionManager.Instance.SelectField(this);
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


}

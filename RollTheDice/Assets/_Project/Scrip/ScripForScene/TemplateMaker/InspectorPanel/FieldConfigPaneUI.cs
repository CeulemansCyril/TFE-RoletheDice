using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.Localization;
using NUnit.Framework;
using System;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;
using static TMPro.TMP_Dropdown;

public class FieldConfigPaneUI : MonoBehaviour
{
    [Header("Rows Data")]
    public GameObject labelRow;
    public GameObject minRow;
    public GameObject maxRow;
    public GameObject optionListRow;
    public GameObject requiered;
    public GameObject typeDropdown;
    public GameObject Title;

    public TMP_Dropdown typeDropdownComp;

    [Header("Rows Size")]
    public GameObject PositionX;
    public GameObject PositionY;
    public GameObject Width;
    public GameObject Heigth;

 

    private FieldUI currentTemplateFieldData;


    public static FieldConfigPaneUI Instance;
    private LocalizedText titleLocalizedText;

    [SerializeField] private GameObject listManagerPopupPrefab;
    [SerializeField] private RectTransform baseUIScreen;


    private void Awake()
    {
        if (Instance != null && Instance != this)
        {
            Destroy(gameObject);
            return;
        }

        Instance = this;
        LoadEvent();

    }




    public void Start()
    {
        minRow.SetActive(false);
        maxRow.SetActive(false);
        optionListRow.SetActive(false);


        LoadDropDownList();


        if (FieldSelectionManager.Instance == null)
        {
            Debug.LogError("FieldSelectionManager.Instance est NULL dans Start");
            return;
        }

        FieldSelectionManager.Instance.OnFieldSelected += OnFieldSelected;
        FieldSelectionManager.Instance.OnFieldCleared += OnFieldCleared;

    }

    public void LoadEvent()
    {
        TMP_InputField tMP_labelRow = labelRow.GetComponentInChildren<TMP_InputField>();
        tMP_labelRow.onValueChanged.AddListener(OnLabelChange);

        TMP_InputField tMP_minRow = minRow.GetComponentInChildren<TMP_InputField>();
        tMP_minRow.onValueChanged.AddListener(OnMinChange);

        TMP_InputField tMP_maxRow = maxRow.GetComponentInChildren<TMP_InputField>();
        tMP_maxRow.onValueChanged.AddListener(OnMaxChange);

        TMP_InputField tMP_positionX = PositionX.GetComponentInChildren<TMP_InputField>();
        tMP_positionX.onValueChanged.AddListener(OnPositionXChange);

        TMP_InputField tMP_positionY = PositionY.GetComponentInChildren<TMP_InputField>();
        tMP_positionY.onValueChanged.AddListener(OnPositionYChange);

        TMP_InputField tMP_heigth = Heigth.GetComponentInChildren<TMP_InputField>();
        tMP_heigth.onValueChanged.AddListener(OnHeigthChange);

        TMP_InputField tMP_width = Width.GetComponentInChildren<TMP_InputField>();
        tMP_width.onValueChanged.AddListener(OnWidthChange);

        typeDropdownComp.onValueChanged.AddListener(OnTypeChanged);

        Toggle requieredToggle = requiered.GetComponentInChildren<Toggle>();
        requieredToggle.onValueChanged.AddListener(OnToggleChange);

        Button optionListButton = optionListRow.GetComponentInChildren<Button>();
        optionListButton.onClick.AddListener(OpenListManagerPopup);


    }

    public void LoadDropDownList() {
        typeDropdownComp.ClearOptions();

         List<OptionData> options = new List<OptionData>();
         options.Add(SetOptionData(LocalizationControllers.Instance.GetLocalizedValue("FieldConfig.Option.Select")));
         options.Add(SetOptionData(LocalizationControllers.Instance.GetLocalizedValue("FieldConfig.Option.Number")));
         options.Add(SetOptionData(LocalizationControllers.Instance.GetLocalizedValue("FieldConfig.Option.Text")));
        options.Add(SetOptionData(LocalizationControllers.Instance.GetLocalizedValue("FieldConfig.Option.Bool")));
        options.Add(SetOptionData(LocalizationControllers.Instance.GetLocalizedValue("FieldConfig.Option.List")));
     


        typeDropdownComp.AddOptions(options);


    }

    public OptionData SetOptionData(string text) {
        OptionData option = new OptionData(text);
        option.color = Color.white;
        return option;
    }

    public void OnTypeChanged(int index)
    {
        if (currentTemplateFieldData == null)
            return;

        minRow.SetActive(false);
        maxRow.SetActive(false);
        optionListRow.SetActive(false);

        switch (index)
        {
            case 1:  
                minRow.SetActive(true);
                maxRow.SetActive(true);
                currentTemplateFieldData.OnDataChange("Int", "type", null);
                break;

            case 2:  
                currentTemplateFieldData.OnDataChange("String", "type", null);
                break;

            case 3:  
                currentTemplateFieldData.OnDataChange("Boolean", "type", null);
                break;

            case 4:  
                optionListRow.SetActive(true);
                currentTemplateFieldData.OnDataChange("ComboBox", "type", null);
                break;
        }
    }


    private void OnLabelChange(string value) {
        if (currentTemplateFieldData == null) return;
        currentTemplateFieldData.OnDataChange(value, "label", null);
    }
    private void OnMinChange(string value)
    {
        if (currentTemplateFieldData == null) return;
        currentTemplateFieldData.OnDataChange(value, "min", null);
    }
    private void OnMaxChange(string value)
    {
        if (currentTemplateFieldData == null) return;
        currentTemplateFieldData.OnDataChange(value, "max", null);
    }

    private void OnWidthChange(string value) {
        if (currentTemplateFieldData == null) return;
        currentTemplateFieldData.OnDataChange(value, "width", null);
    }
    private void OnHeigthChange(string value) {
        if (currentTemplateFieldData == null) return;
        currentTemplateFieldData.OnDataChange(value, "height", null);
    }
    private void OnPositionXChange(string value)
    {
        if (currentTemplateFieldData == null) return;
        currentTemplateFieldData.OnDataChange(value, "positionX", null);
    }
    private void OnPositionYChange(string value)
    {
        if (currentTemplateFieldData == null) return;
        currentTemplateFieldData.OnDataChange(value, "positionY", null);
    }
    private void OnToggleChange(bool value)
    {
        if (currentTemplateFieldData == null) return;
        currentTemplateFieldData.OnDataChange(value.ToString(), "required",null);
    }

    //TODO: Ajouter le load pour l'option list
    public void OpenListManagerPopup()
    {
        if (currentTemplateFieldData == null) return;
        var popupGO = Instantiate(listManagerPopupPrefab, baseUIScreen);

        var popup = popupGO.GetComponent<ListManagerPopUp>();

       
        popupGO.transform.SetAsLastSibling(); 

        popup.OnConfirm = (selectedList) =>
        {
            currentTemplateFieldData.OnDataChange("", "optionList",selectedList);
            Destroy(popupGO);
        };

        popup.OnCancel = () =>
        {
            Destroy(popupGO);
        };
    }



    public void LoadTemplateField(TemplateField templateField) {
        
        labelRow.GetComponentInChildren<TMP_InputField>().text = templateField.Label;

        maxRow.GetComponentInChildren<TMP_InputField>().text = templateField.MaxValue.ToString();
        minRow.GetComponentInChildren<TMP_InputField>().text = templateField.MinValue.ToString();

        PositionX.GetComponentInChildren<TMP_InputField>().text = templateField.PositionX.ToString();
        PositionY.GetComponentInChildren<TMP_InputField>().text = templateField.PositionY.ToString();
        Heigth.GetComponentInChildren<TMP_InputField>().text = templateField.Height.ToString();
        Width.GetComponentInChildren<TMP_InputField>().text = templateField.Width.ToString();
        
        Toggle requieredToggle = requiered.GetComponentInChildren<Toggle>();
        requieredToggle.isOn = templateField.Required;

        switch (templateField.Type)
        {
            case "Int":
                typeDropdownComp.value = 1;
                OnTypeChanged(1);
                break;
            case "String":
                typeDropdownComp.value = 2;
                OnTypeChanged(2);
                break;
            case "Boolean":
                typeDropdownComp.value = 3;
                OnTypeChanged(3);
                break;
            case "ComboBox":
                typeDropdownComp.value = 4;
                OnTypeChanged(4);
                break;
        }

    }

 

    private void OnDisable()
    {
        if (FieldSelectionManager.Instance == null)
            return;

        FieldSelectionManager.Instance.OnFieldSelected -= OnFieldSelected;
        FieldSelectionManager.Instance.OnFieldCleared -= OnFieldCleared;
    }



    private void OnFieldSelected(FieldUI fieldUI)
    {
        if (fieldUI == null) return;
        currentTemplateFieldData = fieldUI;
 
        LoadTemplateField(fieldUI.GetTemplateField());

        gameObject.SetActive(true);
    }

    private void OnFieldCleared()
    {
        currentTemplateFieldData = null;
        gameObject.SetActive(false);
    }

}

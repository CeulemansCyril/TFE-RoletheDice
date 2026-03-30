using Assets._Project.API.Model.Object.Game.Templates;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class BuildPanellDrop : MonoBehaviour, IDropHandler
{
    [SerializeField] private GameObject fieldPrefab;

    private RectTransform rectTransform;


    public void Awake()
    {
        rectTransform = GetComponent<RectTransform>();

    }



    public void OnDrop(PointerEventData eventData)
    {
        if (eventData.pointerDrag == null)
        {
            return;
        }
        if (!eventData.pointerDrag.name.Contains("TemplateFieldUI"))
            return;

        string draggedObjectName = eventData.pointerDrag.name;

        TemplateFieldDragData dragData =
        eventData.pointerDrag.GetComponent<TemplateFieldDragData>();
 

        if (dragData == null)
        {
            return;
        }



        GameObject field = Instantiate(fieldPrefab, transform);
        field.tag = "TemplateFieldUI";

        RectTransform fieldRect = field.GetComponent<RectTransform>();


        Vector2 localPoint;

        RectTransformUtility.ScreenPointToLocalPointInRectangle(
            rectTransform,
            eventData.position,
            eventData.pressEventCamera,
            out localPoint
        );

        fieldRect.anchoredPosition = localPoint;


        FieldUI fieldUI = field.GetComponent<FieldUI>();
        if (fieldUI != null)
        {
            TemplateField templateField = new TemplateField
            {
                Label = "New Field",
                Type = dragData.FieldType.ToString(),
                PositionX = localPoint.x,
                PositionY = localPoint.y,
                Width = 100,
                Height = 30,
                Required = false
            };
            fieldUI.Initialize(templateField);
            FieldSelectionManager.Instance.SelectField(fieldUI);


        }


    }

    public void ClearAllFields()
    {
        GameObject[] fieldObjects = GameObject.FindGameObjectsWithTag("TemplateFieldUI");
        foreach (GameObject obj in fieldObjects)
        {
            Destroy(obj);
        }
    }

    public void LoadFields(List<TemplateField> templateField)
    {
        ClearAllFields();

        foreach (TemplateField fieldData in templateField)
        {
            GameObject field = Instantiate(fieldPrefab, transform);
            field.tag = "TemplateFieldUI";

            FieldUI fieldUI = field.GetComponent<FieldUI>();

            if (fieldUI != null)
            {
                fieldUI.Initialize(fieldData);
            }
            else
            {
                Debug.LogError("FieldUI component missing on prefab");
            }
        }
    }


    public List<TemplateField> GetAllField()
    {
        List<TemplateField> fields = new List<TemplateField>();

        FieldUI[] fieldUIs = GetComponentsInChildren<FieldUI>();

        foreach (FieldUI obj in fieldUIs)
        {
       
            if (obj != null)
            {
                fields.Add(obj.GetTemplateField());
            }
        }

        return fields;
    }
}

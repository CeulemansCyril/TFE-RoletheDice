using UnityEngine;
using UnityEngine.EventSystems;

public class FieldSelect : MonoBehaviour, IPointerClickHandler
{
    private FieldUI fieldUI;


    public void Awake()
    {
        fieldUI = GetComponentInParent<FieldUI>();

    }




    public void OnPointerClick(PointerEventData eventData)
    {
        if (eventData.button != PointerEventData.InputButton.Left)
        {
            Destroy(gameObject);
        }
        else if (eventData.button == PointerEventData.InputButton.Right)
        {
            FieldConfigPaneUI.Instance.LoadTemplateField(fieldUI.GetTemplateField());
        }

     }
}

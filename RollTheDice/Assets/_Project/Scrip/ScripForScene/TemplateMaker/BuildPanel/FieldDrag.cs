using UnityEngine;
using UnityEngine.EventSystems;

public class FieldDrag : MonoBehaviour, IBeginDragHandler, IDragHandler, IEndDragHandler
{
    private RectTransform rectTransform;
    private RectTransform parentTransform;

    private Canvas rootCanvas;

    public void Awake()
    {
        rectTransform = GetComponent<RectTransform>();
        parentTransform = rectTransform.parent as RectTransform;
        rootCanvas = GetComponentInParent<Canvas>();
    }


    public void OnBeginDrag(PointerEventData eventData)
    {
        rectTransform.SetAsLastSibling();
    }

    public void OnDrag(PointerEventData eventData)
    {
        Vector2 localPoint;
        RectTransformUtility.ScreenPointToLocalPointInRectangle(parentTransform, eventData.position,eventData.pressEventCamera,out localPoint);

        rectTransform.anchoredPosition = ClampToParent(localPoint);
    }

    public void OnEndDrag(PointerEventData eventData)
    {
      
    }

    private Vector2 ClampToParent(Vector2 position)
    {
        Vector2 min = parentTransform.rect.min + rectTransform.rect.size * rectTransform.pivot;
        Vector2 max = parentTransform.rect.max - rectTransform.rect.size * (Vector2.one - rectTransform.pivot);

        return new Vector2(
            Mathf.Clamp(position.x, min.x, max.x),
            Mathf.Clamp(position.y, min.y, max.y)
        );
    }
}
   


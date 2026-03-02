using UnityEngine;
using UnityEngine.EventSystems;

public class NavigationMenuButton : MonoBehaviour, IPointerEnterHandler, IPointerExitHandler, IPointerClickHandler
{
    [SerializeField] private GameObject Perfab;
    [SerializeField] private GameObject Parent;

    public void OnPointerClick(PointerEventData eventData)
    {
        ClearParent();

        Transform transform = Parent.transform;

        var obj2 = Instantiate(Perfab, transform);

    }

    public void OnPointerEnter(PointerEventData eventData)
    {
 
    }

    public void OnPointerExit(PointerEventData eventData)
    {
 

    }

    private void ClearParent()
    {
        Transform parent = Parent.transform;
        foreach (Transform child in parent)
        {
            Destroy(child.gameObject);
        }
    }
}



using Assets._Project.API.Model.Object.Game.Templates;
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

public class CustomObjectItem : MonoBehaviour,IPointerExitHandler, IPointerEnterHandler
{
    [SerializeField] public Button DeleteButon;
    [SerializeField] public Button ModifButon;
    [SerializeField] public TMP_Text CustomObjectName;
    [SerializeField] public Image BookCursor;
    [SerializeField] public Color NormalColor;
    [SerializeField] public Color HoverColor;
    public CustomObject Custom {  get;  set; }


    private void Awake()
    {
         if (BookCursor == null) BookCursor = GetCursor();
    }

    public void SetTitle(string title)
    {
        CustomObjectName.text = title;
    }


    private Image GetCursor()
    {

        var images = GetComponentsInChildren<Image>(true);

        foreach (var img in images)
        {
            if (img.gameObject.name == "cursor")
                return img;
        }


        return null;
    }

    public void OnPointerEnter(PointerEventData eventData)
    {
 
 
     
        BookCursor.color = HoverColor;
    }

    public void OnPointerExit(PointerEventData eventData)
    {
       
        BookCursor.color = NormalColor;
    }
}

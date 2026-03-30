using Assets._Project.API.Model.Object.Game.Book;
 
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

public class BookItem : MonoBehaviour,IPointerExitHandler, IPointerEnterHandler
{
    [SerializeField] public Button DeleteButon;
    [SerializeField] public Button ModifButon;
    [SerializeField] public TMP_Text BookName;
    [SerializeField] public Image BookCursor;
    [SerializeField] public Color NormalColor;
    [SerializeField] public Color HoverColor;
    public Books Book { set; get; }


    private void Awake()
    {
         if (BookCursor == null) BookCursor = GetCursor();
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

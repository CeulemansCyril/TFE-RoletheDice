using Assets._Project.API.Model.Object.Game;
using Assets._Project.API.Model.Object.Game.Book;
 
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

public class BundleItem : MonoBehaviour,IPointerExitHandler, IPointerEnterHandler
{
    [SerializeField] public Button DeleteButon;
    [SerializeField] public Button ModifButon;
    [SerializeField] public TMP_Text BundleName;
    [SerializeField] public Image BundleCursor;
    [SerializeField] public Color NormalColor;
    [SerializeField] public Color HoverColor;
    public GameBundle Bundle { set; get; }


    private void Awake()
    {
         if (BundleCursor == null) BundleCursor = GetCursor();
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
 
 
     
        BundleCursor.color = HoverColor;
    }

    public void OnPointerExit(PointerEventData eventData)
    {
       
        BundleCursor.color = NormalColor;
    }

    public void setBundle(GameBundle bundle) { 
        Bundle = bundle;
        BundleName.text = bundle.Name;
    }
}

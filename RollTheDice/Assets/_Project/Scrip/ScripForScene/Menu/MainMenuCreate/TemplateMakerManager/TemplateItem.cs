using Assets._Project.API.Model.Object.Game.Templates;
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

public class TemplateItem : MonoBehaviour,IPointerExitHandler, IPointerEnterHandler
{
    [SerializeField] public Button DeleteButon;
    [SerializeField] public Button ModifButon;
    [SerializeField] public TMP_Text TemplateName;
    [SerializeField] public Image TemplateCuror;
    [SerializeField] public Color NormalColor;
    [SerializeField] public Color HoverColor;
    public Template Template { set; get; }


    private void Awake()
    {
         if (TemplateCuror == null) TemplateCuror = GetCursor();
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
 
 
     
        TemplateCuror.color = HoverColor;
    }

    public void OnPointerExit(PointerEventData eventData)
    {
       
        TemplateCuror.color = NormalColor;
    }
}

using System;
using UnityEngine;
using UnityEngine.EventSystems;

public class TemplateFieldDrag : MonoBehaviour, IBeginDragHandler, IDragHandler, IEndDragHandler
{

    [Header("Ghost settings")]
    public float ghostAlphas = 0.6f;

    private GameObject ghost;
    private RectTransform ghostRect;

    private Canvas rootCanvas;


    public void Awake()
    {
       
        rootCanvas = GetComponentInParent<Canvas>();
    }



    public void OnBeginDrag(PointerEventData eventData)
    {
       ///creation ghost 
       ghost = Instantiate(gameObject,rootCanvas.transform);
       ghost.name = gameObject.name + "_Ghost";

       ghostRect = ghost.GetComponent<RectTransform>();
       ghostRect.position = eventData.position;
        

      CanvasGroup cg = ghost.GetComponent<CanvasGroup>();
      
      if (cg == null) { cg = ghost.AddComponent<CanvasGroup>(); }  

      cg.alpha = ghostAlphas;
      cg.blocksRaycasts = false;  

     
    }

    public void OnDrag(PointerEventData eventData)
    {
      
        if (ghostRect != null)
        {
            ghostRect.position = eventData.position;
        }
    }

    public void OnEndDrag(PointerEventData eventData)
    {
      
        if (ghost != null)
        {
          
            Destroy(ghost);
        }
    }
}

using Assets._Project.Scrip.Accordion;
using System.Collections;
using UnityEditor.Experimental.GraphView;
using UnityEngine;
using UnityEngine.UI;

public class AccordionSection : MonoBehaviour
{
    [Header("References")]
    [SerializeField] private RectTransform content;
    [SerializeField] private RectTransform contentInner;
    [SerializeField] private Button headerButton;
    [SerializeField] private Accordion accordionParent;

    [Header("Animation")]
    [SerializeField] private float animationDuration = 0.25f;
    [SerializeField] private bool isOpen = false;
    [SerializeField] private AccordionDirection accordionDirection = AccordionDirection.VerticalBottom;

    private LayoutElement layoutElement;
    private Coroutine animationCoroutine;
   private RectTransform rectAccordion;

    private void Awake()
    {
        layoutElement = content.GetComponent<LayoutElement>();
        if (headerButton != null)
        {
            headerButton.onClick.AddListener(ToggleSection);
        }

        rectAccordion = accordionParent.GetComponent<RectTransform>();

        SetUpPivot();
        ApplyInstant();
    }




    private void SetUpPivot()
    {

        switch (accordionDirection)
        {
            case AccordionDirection.VerticalBottom:
                content.pivot = new Vector2(0.5f, 1f);
                break;
            case AccordionDirection.VerticalTop:
                content.pivot = new Vector2(0.5f, 0f);
                
                break;
            case AccordionDirection.HorizontalRigth:
                content.pivot = new Vector2(-1.5f, 0.5f);
                break;
            case AccordionDirection.HorizontalLeft:
                content.pivot = new Vector2(1.5f, 0.5f);
                break;
        }

    }

    private void ToggleSection()
    {
        isOpen = !isOpen;
        accordionParent.SetOpenState(isOpen);

        if (animationCoroutine != null)
        {
            StopCoroutine(animationCoroutine);
        }

        animationCoroutine = StartCoroutine(AnimateSection());
 
    }



    private IEnumerator AnimateSection()
    {
        float startSize = GetCurrentSize();
        float targetSize = isOpen
            ? (IsVertical() ? GetContentHeight() : GetContentWidth())
            : 0f;

        float elapsedTime = 0f;

        while (elapsedTime < animationDuration)
        {
            elapsedTime += Time.deltaTime;
            float t = Mathf.Clamp01(elapsedTime / animationDuration);
            float easedT = EaseOutCubic(t);

            float currentSize = Mathf.Lerp(startSize, targetSize, easedT);
            ApplyPosition(currentSize);
            ApplySize(currentSize);
           


            yield return null;
        }
        ApplyPosition(targetSize);
        ApplySize(targetSize);
        

    }


    private void ApplySize(float value)
    {
         
        layoutElement.preferredHeight = value;

        
        if (accordionDirection == AccordionDirection.HorizontalLeft ||
            accordionDirection == AccordionDirection.HorizontalRigth)
        {
            layoutElement.preferredWidth = value;
        }

        content.sizeDelta = new Vector2(
            IsVertical() ? content.sizeDelta.x : layoutElement.preferredWidth,
            layoutElement.preferredHeight);
    }


    private void ApplyPosition(float size)
    {
        Vector2 pos = Vector2.zero;

        switch (accordionDirection)
        {
            case AccordionDirection.VerticalBottom:
                pos.y = -size * 0.5f;
                break;

            case AccordionDirection.VerticalTop:
                pos.y = size * 0.5f;
                break;

            case AccordionDirection.HorizontalRigth:
                pos.x = -size * 0.5f;
                pos.y = GetHeaderLocalY();
                content.anchoredPosition = pos;
                break;

            case AccordionDirection.HorizontalLeft:
                pos.x = size * 0.5f;
                pos.y = GetHeaderLocalY();
                content.anchoredPosition = pos;
                break;
        }

        
        contentInner.anchoredPosition = new Vector2(0, 0);
    }



    public void SetClose()
    {
        isOpen = false;


        if (animationCoroutine != null)
        {
            StopCoroutine(animationCoroutine);
        }

  

        animationCoroutine = StartCoroutine(AnimateSection());
    }


    private float GetCurrentSize() { return accordionDirection == AccordionDirection.VerticalBottom || accordionDirection == AccordionDirection.VerticalTop ? layoutElement.preferredHeight : layoutElement.preferredWidth; }



    private float GetContentHeight()
    {
        LayoutRebuilder.ForceRebuildLayoutImmediate(contentInner);
        return contentInner.rect.height;
    }

    private float GetContentWidth()
    {
        LayoutRebuilder.ForceRebuildLayoutImmediate(contentInner);
        return contentInner.rect.width;
    }

    private void ApplyInstant()
    {
        float size = isOpen
            ? (IsVertical() ? GetContentHeight() : GetContentWidth())
            : 0f;

        layoutElement.preferredHeight = size;

        if (accordionDirection == AccordionDirection.HorizontalLeft ||
            accordionDirection == AccordionDirection.HorizontalRigth)
        {
            layoutElement.preferredWidth = size;
        }

        ApplyPosition(size);
    }
    private float GetHeaderLocalY()
    {
        RectTransform headerRect = headerButton.GetComponent<RectTransform>();

        Vector3 worldPos = headerRect.TransformPoint(headerRect.rect.center);
        Vector3 localPos = rectAccordion.parent.InverseTransformPoint(worldPos);

        return localPos.y;
    }


    private float EaseOutCubic(float t)
    {
        return 1f - Mathf.Pow(1f - t, 3f);
    }

    private bool IsVertical()
    {
        return accordionDirection == AccordionDirection.VerticalBottom
            || accordionDirection == AccordionDirection.VerticalTop;
    }

}

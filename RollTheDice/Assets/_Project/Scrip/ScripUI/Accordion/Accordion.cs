using System;
using UnityEngine;
using UnityEngine.UI;

public class Accordion : MonoBehaviour
{
    private bool IsOpen;

    public Action<Accordion> EventOnValueChanged { get; internal set; }

    public void SetOpenState(bool isOpen)
    {
        IsOpen = isOpen;
        EventOnValueChanged?.Invoke(this);
      
    }

    public bool GetOpenState()
    {
        return IsOpen;
    }

    public void CloseState()
    {
        AccordionSection accordionSection = GetComponentInChildren<AccordionSection>();
    
        accordionSection.SetClose();

        IsOpen = false;
    }
}

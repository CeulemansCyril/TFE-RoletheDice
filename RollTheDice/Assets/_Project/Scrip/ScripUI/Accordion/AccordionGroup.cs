using NUnit.Framework;
using System.Collections.Generic;
using UnityEngine;

public class AccordionGroup : MonoBehaviour
{
    [SerializeField] public bool allowMultipleOpenSections = false;
    private Accordion[] sections;

    public void Awake()
    {
        sections = GetComponentsInChildren<Accordion>();

        foreach (var section in sections)
        {
            section.EventOnValueChanged += OnValueChanged;
        }
    }

    public void CloseOtherSections(Accordion openedSection)
    {
        if (!allowMultipleOpenSections)
        {
            foreach (var section in sections)
            {
                if (section != openedSection && section.GetOpenState())
                {
                    section.CloseState();
                }
            }
        }
    }

    public void OnValueChanged(Accordion changedSection)
    {
        
        CloseOtherSections(changedSection);
    }
}

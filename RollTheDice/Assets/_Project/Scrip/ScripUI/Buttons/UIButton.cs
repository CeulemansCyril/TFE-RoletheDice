using Assets._Project.Localization;
using TMPro;
using UnityEngine;
using UnityEngine.Events;
using UnityEngine.UI;

public class UIButton : MonoBehaviour
{
    public Button button;
    public TMP_Text label;
     


    public void SetInteractable(bool value)
    {
        button.interactable = value;
    }

    public void SetClickListener(UnityAction action)
    {
        button.onClick.RemoveAllListeners();
        button.onClick.AddListener(action);
    }

}

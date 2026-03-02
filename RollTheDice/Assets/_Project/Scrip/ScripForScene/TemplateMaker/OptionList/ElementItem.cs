using System;
using UnityEngine;
using UnityEngine.UI;

public class ElementItem : MonoBehaviour
{
    

    private String elementName;
    public void Setup(string name)
   {
       var text = GetComponentInChildren<TMPro.TMP_Text>();
       if (text != null)
       {
           text.text = name;
           elementName = name;
        }
       else
       {
           Debug.LogError("TMP_Text component not found in children.", this);
       }

       Button deleteButton = GetComponentInChildren<Button>();
         if (deleteButton != null)
         {
              deleteButton.onClick.AddListener(OnDeleteButtonClicked);
         }
         else
         {
              Debug.LogError("Button component not found in children.", this);
        }

    }

    public string GetElementName() {
       return elementName;
    }

    public void OnDeleteButtonClicked()
    {
    
         Destroy(gameObject);
    }


}

using Assets._Project.API.Model.Object.Game.Templates;
using System;
using System.Collections.Generic;
using TMPro;
using UnityEditor.ShaderGraph;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

[RequireComponent(typeof(Button))]
public class ListItem : MonoBehaviour, IPointerEnterHandler, IPointerExitHandler
{
    [Header("Colors")]
    [SerializeField] private Color normalColor;
    [SerializeField] private Color highlightColor ;


    private Image cursor  ;
    private Button button;
    private TMP_InputField renameInput;
    private TMP_Text title;
    private bool isActive = false;

    private string oldName = "";
    public Func<string, bool> ListAlreadyExist;
    public Action<String, ListItem> ListOpt { get; internal set; }
    public System.Action OnRename;


    public void SetUpList(string name)
    {
        TMP_Text title = GetTitle();
        this.title = title;

        title.text = name;
    }

    private void Awake()
    {
        if (cursor == null) cursor = GetCursor();
        if (button == null)
        {
            button = GetComponent<Button>();
            button.onClick.AddListener(ButClick);
        }
        if (renameInput == null) {
            renameInput = GetRename();
            renameInput.gameObject.SetActive(false);
            renameInput.onEndEdit.AddListener(EndRename);

        }
 

    }

    public void StartRename()
    {
        oldName = title.text;

        button.interactable = false;
        title.gameObject.SetActive(false);

        renameInput.gameObject.SetActive(true);
        renameInput.text = oldName;
        renameInput.Select();
        renameInput.ActivateInputField();
        
    }

    private void EndRename(string newName)
    {
        if (string.IsNullOrWhiteSpace(newName))
            newName = oldName;

        else if (ListAlreadyExist != null && ListAlreadyExist(newName)) newName = oldName;

        title.text = newName;

        renameInput.gameObject.SetActive(false);
        title.gameObject.SetActive(true);

        button.interactable = true;

        OnRename?.Invoke( );
    }


    private void ButClick()
    {
        string title = GetTitle().text ;
        isActive = true;
        ListOpt?.Invoke(title, this);
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

    public TMP_Text GetTitle()
    {

        var text = GetComponentsInChildren<TMP_Text>(true);

        foreach (var tx in text)
        {
            if (tx.gameObject.name == "Title")
                return tx;
        }

        
        return null;
    }

    public TMP_InputField GetRename()
    {
         
            var inputs = GetComponentsInChildren<TMP_InputField>(true);
            foreach (var inp in inputs)
            {
                if (inp.gameObject.name == "Rename")
                renameInput = inp;
            }
         
        return renameInput;
    }

    public void OnPointerEnter(PointerEventData eventData)
    {
        
        cursor.color = highlightColor;

    }

    public void OnPointerExit(PointerEventData eventData)
    {
       
       if(!isActive) cursor.color = normalColor;
       else if(isActive && cursor.color!=Color.green) ButStayActiver();
    }

    public void ButStayActiver() 
    { 
        cursor.color = Color.green;
    }

    public void ButStayDesactiver()
    {
        cursor.color = normalColor;
        isActive = false;
    }


}


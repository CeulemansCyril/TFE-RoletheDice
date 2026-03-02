using Assets._Project.API.Model.Object.Game.Templates;
using System;
using System.Collections.Generic;
using System.Linq;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class ListManager : MonoBehaviour
{
    [SerializeField]  private GameObject ListItem;
    [SerializeField] private GameObject ElementItem;
 

    [SerializeField] private RectTransform contentElementItem;
    [SerializeField] private RectTransform contentListElement;

    [SerializeField] private RectTransform AddRowListElement;
    [SerializeField] private RectTransform AddRowElement;

    [SerializeField] private Button deleteList;
    [SerializeField] private Button renameList;


     private CanvasGroup addRowCanvasGroup;
   


    private Button addListButton;
    private Button addElementButton;

    private TMP_InputField addListInput;
    private TMP_InputField addElementInput;


    private List<OptionList> options = new List<OptionList>();
    private OptionList activeList;

    private ListItem activeListButton;

    private bool AddElementsAreEnabler;


    private void Awake()
    {
        BindAddListUI();
        BindAddElementUI();
        BindActionButton();

        AddElementsAreEnabler = false;

    }

    private void BindAddListUI()
    {
        if (AddRowListElement == null)
        {
            Debug.LogError("AddRowListElement not assigned", this);
            return;
        }

        addListButton = AddRowListElement.GetComponentInChildren<Button>(true);
        addListInput = AddRowListElement.GetComponentInChildren<TMP_InputField>(true);

        if (addListButton == null)
            Debug.LogError("Button not found in AddRowListElement", this);

        if (addListInput == null)
            Debug.LogError("TMP_InputField not found in AddRowListElement", this);
    }

    private void BindAddElementUI()
    {
        if (AddRowElement == null)
        {
            Debug.LogError("AddRowElement not assigned", this);
            return;
        }

        addElementButton = AddRowElement.GetComponentInChildren<Button>(true);
        addElementInput = AddRowElement.GetComponentInChildren<TMP_InputField>(true);
        addRowCanvasGroup = AddRowElement.GetComponent<CanvasGroup>();

        if (addElementButton == null)
            Debug.LogError("Button not found in AddRowElement", this);

        if (addElementInput == null)
            Debug.LogError("TMP_InputField not found in AddRowElement", this);

    
    }

   private void BindActionButton()
    {
        if (deleteList != null)
        {
            deleteList.onClick.AddListener(DeleteList);
        }

        if (renameList != null)
        {
           renameList.onClick.AddListener(RenameList);
        }
    }



    public void LoadListManager (List<OptionList> options)
    {
        this.options = options;

        foreach (OptionList option in options)
        {
            CreateList(option.Name);
        }
    }

    private void LoadListElement(OptionList list)
    {
        List<string> Elements = list.Options;

        if(Elements == null) return;

        foreach (string element in Elements) {
            CreateElement(element);
        }

        if(!AddElementsAreEnabler) EnableElement(true);
    }


    private void CreateElement(string element) {

        Transform transform = contentElementItem.transform;

        var go = Instantiate(ElementItem, transform);


        var item = go.GetComponent<ElementItem>();

        

        item.Setup(element);
         
    }

    private void CreateList(string nameList)
    {
        Transform transform = contentListElement.transform;
        var go = Instantiate(ListItem, transform);
        var item = go.GetComponent<ListItem>();

        item.SetUpList(nameList);

        item.ListOpt += OnListClicked;

        OptionList list = new OptionList();
        list.Name = nameList;

        options.Add(list);


    }

    private void DeleteList( )
    {
        if(activeList == null) return;
        foreach(Transform child in contentListElement)
        {
            var item = child.GetComponent<ListItem>();
            if(item != null)
            {
                if(item.GetTitle().text == activeList.Name)
                {
                    Destroy(child.gameObject);
                    options.Remove(activeList);
                    activeList = null;
                    EmptyListElement();
                    break;
                }
            }
        }
    }

    private void RenameList()
    {
        if (activeList == null || activeListButton == null) return;
        activeListButton.StartRename();

    }

    private void EmptyListElement()
    {
        foreach (Transform child in contentElementItem)
        {
            Destroy(child.gameObject);
        }
    }

    private void UpdateListElment() { 
    
        List<string> elements = new List<string>();
        ElementItem[] elementItems = contentElementItem.GetComponentsInChildren<ElementItem>();

        foreach (ElementItem elementItem in elementItems)
        {
            elements.Add(elementItem.GetElementName());
        }

        activeList.Options = elements;
     
    }

   
    public void OnListClicked(string name, ListItem button)
    {
        if (activeList != null && activeList.Name == name)
            return;

        if (activeList != null) UpdateListElment();
        EmptyListElement();
        activeList = FindOptionListByName(name);
        LoadListElement(activeList);

        if(activeListButton != null && activeListButton != button)
        {
            activeListButton.ButStayDesactiver();
            activeListButton.OnRename -= EndRename;
        }
        activeListButton = button;
        activeListButton.ButStayActiver();
        activeListButton.OnRename += EndRename;
        activeListButton.ListAlreadyExist = ListAlreadyExist;

    }

    private void EndRename()
    {
        EmptyListElement();

    }

    public OptionList FindOptionListByName(string name)
    {
        foreach (OptionList option in options)
        {
            if (option.Name == name) return option;
        }
        return null;
    }
    
    public void AddListElement()
    {
   
        if(addListInput == null) return;

 
        string txt = addListInput.text;

        if(!string.IsNullOrWhiteSpace(txt)) 
        {
            if (!ListAlreadyExist(txt))
            {
                CreateList(txt);
               
            }

            addListInput.text = "";
        }

    }

    public void AddElement()
    {
        if (addElementInput == null || activeList == null) return;
        string txt = addElementInput.text;

        if (!string.IsNullOrWhiteSpace(txt))
        {
            if (!ElementAlreadyExist(txt))
            {
                CreateElement(txt);
                activeList.Options.Add(txt);
            }

            addElementInput.text = "";
        }
    }

    public bool ListAlreadyExist(string name)
    {
        foreach (OptionList option in options)
        {
            if(option.Name == name) return true;
        }
        return false;
    }

    private bool ElementAlreadyExist(string name)
    {
        foreach(string option in activeList.Options) { 
            if(option == name) return true;
        }
        return false;
    }

    private void EnableElement(bool enabled)
    {
        if (AddRowElement == null)
        {        
            return;
        }

        if (addRowCanvasGroup == null)
        {
            return;
        }
        
        addRowCanvasGroup.alpha = enabled ? 1f : 0.4f;
        addRowCanvasGroup.interactable = enabled;
        addRowCanvasGroup.blocksRaycasts = enabled;
       

     
        AddElementsAreEnabler = enabled;
    }

   

    private void OnEnable()
    {
        if (addListButton != null)
            addListButton.onClick.AddListener(AddListElement);

        if (addElementButton != null)
            addElementButton.onClick.AddListener(AddElement);

        EnableElement(false);
    }

    private void OnDisable()
    {
        if (addListButton != null)
            addListButton.onClick.RemoveListener(AddListElement);

        if (addElementButton != null)
            addElementButton.onClick.RemoveListener(AddElement);
    }

    public OptionList GetActiveList()
    {
        return activeList;
    }
 

}

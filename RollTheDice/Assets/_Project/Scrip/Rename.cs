using System;
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;

public class Rename : MonoBehaviour, IPointerClickHandler
{
    [SerializeField] private TMP_Text title;
    [SerializeField] private TMP_InputField renameInput;

    private string oldName = "";
    private bool isRenaming = false;

    public Action endRename;
    public Action click;

    private void Awake()
    {
        renameInput.gameObject.SetActive(false);
        title.gameObject.SetActive(true);

        renameInput.onEndEdit.AddListener(EndRename);
        renameInput.onSubmit.AddListener(EndRename);
        renameInput.onDeselect.AddListener(_ => EndRename(renameInput.text));
    }


    public void StartRename()
    {
        isRenaming = true;
        oldName = title.text;

        title.gameObject.SetActive(false);

        renameInput.gameObject.SetActive(true);
        renameInput.text = oldName;
        renameInput.Select();
        renameInput.ActivateInputField();

    }

    private void EndRename(string newName)
    {
        if (!isRenaming) return;
        isRenaming = false;

        if (string.IsNullOrWhiteSpace(newName))
            newName = oldName;

      

        title.text = newName;

        renameInput.gameObject.SetActive(false);
        title.gameObject.SetActive(true);
        Debug.Log("EndRename");
        endRename?.Invoke();
 
    }

    public void OnPointerClick(PointerEventData eventData)
    {
        StartRename();
        click?.Invoke();
    }
}

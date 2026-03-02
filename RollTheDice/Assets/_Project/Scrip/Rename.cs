using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;

public class Rename : MonoBehaviour, IPointerClickHandler
{
    [SerializeField] private TMP_Text title;
    [SerializeField] private TMP_InputField renameInput;

    private string oldName = "";

    private void Awake()
    {
        renameInput.gameObject.SetActive(false);
        title.gameObject.SetActive(true);
        
        renameInput.onEndEdit.AddListener(EndRename);
    }


    public void StartRename()
    {
        oldName = title.text;

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

      

        title.text = newName;

        renameInput.gameObject.SetActive(false);
        title.gameObject.SetActive(true);

 
    }

    public void OnPointerClick(PointerEventData eventData)
    {
        StartRename();
    }
}

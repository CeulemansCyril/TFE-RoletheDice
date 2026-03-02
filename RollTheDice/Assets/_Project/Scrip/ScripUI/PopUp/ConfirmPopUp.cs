using System;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class ConfirmPopUp : MonoBehaviour
{
    [SerializeField] private Button closeButton;
    [SerializeField] private Button confirmButton;
    [SerializeField] private Button cancelButton;

    [SerializeField] private TMP_Text messageText;
    [SerializeField] private TMP_Text titleMessage;

    private Action onConfirm;
    private Action onCancel;

    public void Show(string Title,string Message, Action OnConfirm,Action OnCancel)
    {
        onConfirm = OnConfirm;
        onCancel = OnCancel;
        titleMessage.text = Title;
        messageText.text = Message;

        closeButton.onClick.RemoveAllListeners();
        confirmButton.onClick.RemoveAllListeners();
        cancelButton.onClick.RemoveAllListeners();

        closeButton.onClick.AddListener(() => ActionClose());
        confirmButton.onClick.AddListener(() => ActionValide());
        cancelButton.onClick.AddListener(() => ActionCancel());

        gameObject.SetActive(true);
    }

    private void ActionClose()
    {
       gameObject.SetActive(false);
       Destroy(gameObject);
    }

    private void ActionValide()
    {
        onConfirm?.Invoke();
        ActionClose();
 
    }

    private void ActionCancel()
    {
        onCancel?.Invoke();
        ActionClose();
    }
}

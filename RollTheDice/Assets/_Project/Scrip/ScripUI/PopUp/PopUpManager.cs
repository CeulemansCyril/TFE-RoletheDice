using UnityEngine;

public class PopUpManager : MonoBehaviour
{
   public static PopUpManager Instance { get; private set; }

    [SerializeField] private ConfirmPopUp confirmPopUpPrefab;
    [SerializeField] private InputPopUp inputPopUp;
    private void Awake()
    {
        if (Instance != null && Instance != this)
        {
            Destroy(gameObject);
        }
        else
        {
            Instance = this;
            DontDestroyOnLoad(gameObject);
        }
        

    }
    public void ShowConfirmPopUp(string title, string message, System.Action onConfirm, System.Action onCancel)
    {
        ConfirmPopUp popUp = Instantiate(confirmPopUpPrefab, transform);
        popUp.Show(title, message, onConfirm, onCancel);
    }

    public void ShowInputPopUp(string title, string message, System.Action<string> onConfirm, System.Action onCancel)
    {
  
        InputPopUp popUp = Instantiate(inputPopUp, transform);
        
        popUp.Show(title, message, onConfirm, onCancel);
    }
}

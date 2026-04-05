using Assets._Project.API.Model.Object.Game.Templates;
using System;
using UnityEngine;

public class FieldSelectionManager : MonoBehaviour
{
    private static FieldSelectionManager _instance;
    public static FieldSelectionManager Instance
    {
        get
        {
            if (_instance == null)
            {
                
                _instance = FindObjectOfType<FieldSelectionManager>();
                if (_instance == null)
                {
         
                    var go = new GameObject("FieldSelectionManager");
                    _instance = go.AddComponent<FieldSelectionManager>();
                }
            }

            return _instance;
        }
        private set => _instance = value;
    }

    public FieldUI ActiveField { get; private set; }

    public event Action<FieldUI> OnFieldSelected;
    public event Action OnFieldCleared;

    private void Awake()
    {
        if (Instance != null && Instance != this)
        {
            Destroy(gameObject);
            return;
        }
        Instance = this;
       
    }

    public void SelectField(FieldUI field)
    {
        ActiveField = field;
        OnFieldSelected?.Invoke(field);
    }

    public void ClearSelection()
    {
        ActiveField = null;
        OnFieldCleared?.Invoke();
    }
}

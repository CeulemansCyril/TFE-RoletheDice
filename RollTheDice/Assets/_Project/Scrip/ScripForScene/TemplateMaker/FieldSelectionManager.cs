using Assets._Project.API.Model.Object.Game.Templates;
using System;
using UnityEngine;

public class FieldSelectionManager : MonoBehaviour
{
    public static FieldSelectionManager Instance { get; private set; }

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

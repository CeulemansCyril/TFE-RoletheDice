using UnityEngine;

public class Table : MonoBehaviour
{
    [SerializeField] private Transform content;

    public GameObject AddRow(GameObject prefab)
    {
        return Instantiate(prefab, content);
    }

    public void Clear()
    {
        foreach (Transform child in content)
        {
            Destroy(child.gameObject);
        }
    }

    public T[] GetRows<T>() where T : MonoBehaviour
    {
        return content.GetComponentsInChildren<T>();
    }
}

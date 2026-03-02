using UnityEngine;

public class PrefabManager : MonoBehaviour
{
    [SerializeField] private  Transform transform;

    private GameObject prefabInstance;

    public void InstantiatePrefab(GameObject prefab)
    {
        if (prefabInstance != null)
        {
            Destroy(prefabInstance);
        }
        prefabInstance = Instantiate(prefab, transform);
    }
}

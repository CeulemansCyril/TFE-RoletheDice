using Assets._Project.Scrip.Scene;
using UnityEngine;
using UnityEngine.UI;

public class LootTableMaker : MonoBehaviour
{
    [SerializeField] private Button button;
    void Start()
    {
        button.onClick.AddListener(LoadScene);
    }

    private void LoadScene()
    {
        SceneLoader.Instance.LoadScene(Scene.LootTableMaker);
    }

    
}

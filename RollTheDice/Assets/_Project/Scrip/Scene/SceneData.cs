using System.Collections.Generic;
using UnityEditor.Localization.Plugins.XLIFF.V20;
using UnityEngine;

public static class SceneData 
{
    private static Dictionary<string, object> data = new Dictionary<string, object>();

    public static void SetData<T>(string key, T value)
    {
        data[key] = value;
    }

    public static T GetData<T>(string key)
    {
        if (data.TryGetValue(key, out object value) && value is T typedValue)
        {
            return typedValue;
        }
        return default(T);
    }

    public static bool HasData(string key)
    {
        return data.ContainsKey(key);
    }

   public static void RemoveData(string key)
    {
            data.Remove(key);
    }

    public static void Clear()
    {
        data.Clear();
    }
}

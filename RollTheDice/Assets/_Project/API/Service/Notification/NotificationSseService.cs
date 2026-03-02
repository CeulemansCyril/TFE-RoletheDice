using Assets._Project.API.Config;
using Assets._Project.API.Model.DTO;
using Newtonsoft.Json;
using System;
using System.Collections;
using UnityEngine;
using UnityEngine.Networking;

public class NotificationSseListener : MonoBehaviour
{
    private UnityWebRequest request;
    private bool listening;
    private int lastIndex;

    public Action<NotificationDTO> OnNotificationReceived;

    public void StartListening(long userId)
    {
        listening = true;
        StartCoroutine(Listen(userId));
    }

    public void StopListening()
    {
        listening = false;
        request?.Abort();
    }

    private IEnumerator Listen(long userId)
    {
        string url =
            APIConfig.BASE_URL + "api/Notifications/stream/" + userId;

        request = UnityWebRequest.Get(url);
        request.downloadHandler = new DownloadHandlerBuffer();
        request.timeout = 0;
        request.SetRequestHeader("Accept", "text/event-stream");

        request.SendWebRequest();

        lastIndex = 0;

        while (listening && !request.isDone)
        {
            string text = request.downloadHandler.text;

            if (text.Length > lastIndex)
            {
                string chunk = text.Substring(lastIndex);
                lastIndex = text.Length;

                ParseSse(chunk);
            }

            yield return null;
        }
    }

    private void ParseSse(string data)
    {
        string[] lines = data.Split('\n');

        foreach (string line in lines)
        {
            if (line.StartsWith("data:"))
            {
                string json = line.Substring(5).Trim();
                NotificationDTO dto =
                    JsonConvert.DeserializeObject<NotificationDTO>(json);
                OnNotificationReceived?.Invoke(dto);
            }
        }
    }
}

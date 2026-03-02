using Assets._Project.API.Config;
using Assets._Project.API.Model.DTO.Login;
using Newtonsoft.Json;
using System;
using System.Collections;
using System.Collections.Generic;

using System.Text;
using UnityEngine;
using UnityEngine.Networking;


namespace Assets._Project.API.Interface
{
    public abstract class APILoginService
    {
        protected readonly string baseUrl;

        protected APILoginService(string endPoint)
        {
            baseUrl = $"{APIConfig.BASE_URL}{endPoint}";
            
        }
       

        protected UnityWebRequest CreateRequest(
           string url,
           string method,
           string jsonBody = null)
        {
            UnityWebRequest request = new UnityWebRequest(url, method);

            if (jsonBody != null)
            {
                request.uploadHandler =
                    new UploadHandlerRaw(Encoding.UTF8.GetBytes(jsonBody));
            }

            request.downloadHandler = new DownloadHandlerBuffer();
            request.timeout = APIConfig.Timeout;

            foreach (var header in APIConfig.DefaultHeaders)
            {
                request.SetRequestHeader(header.Key, header.Value);
            }

            request.SetRequestHeader("Content-Type", "application/json");

            return request;
        }

        protected IEnumerator Send<TResponse>(
            UnityWebRequest request,
            Action<TResponse> onSuccess,
            Action<string> onError)
        {
            yield return request.SendWebRequest();

            if (request.result != UnityWebRequest.Result.Success)
            {
                string errorMessage = !string.IsNullOrEmpty(request.downloadHandler.text)
                    ? request.downloadHandler.text
                    : request.error;

                onError?.Invoke(errorMessage);
                yield break;
            }

            TResponse response =
                JsonConvert.DeserializeObject<TResponse>(
                    request.downloadHandler.text
                );

            onSuccess?.Invoke(response);
        }

        protected IEnumerator Post<TRequest, TResponse>(
            string endpoint,
            TRequest data,
            System.Action<TResponse> onSuccess,
            System.Action<string> onError)
        {
            
            string json = JsonConvert.SerializeObject(data);
            Debug.Log(JsonUtility.ToJson(data));
            Debug.Log("json: " + json);
            using (var request =
                CreateRequest(baseUrl + endpoint, "POST", json))
            {
               yield return Send(request,onSuccess,onError);
            }
        }

        protected IEnumerator Post<TResponse>(
            string endpoint,
            System.Action<TResponse> onSuccess,
            System.Action<string> onError)
        {
            Debug.Log("API Base URL: " + baseUrl + endpoint);
            using (var request =
                CreateRequest(baseUrl + endpoint, "POST"))
            {
                yield return Send(request, onSuccess, onError);
            }
        }
    }
}

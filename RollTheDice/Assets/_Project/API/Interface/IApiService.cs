using System.Collections.Generic;
using System.Threading.Tasks;

using RollTheDice.API.Config;
using System.Net.Http;

using System.Collections;
using UnityEngine;
using UnityEngine.Networking;
using Newtonsoft.Json;
using System.Text;



namespace RollTheDice.API.Interface
{
    public abstract class IApiService<T>
    {
        protected readonly HttpClient _httpClient;
        protected readonly string baseUrl;

        protected IApiService(string endpoint)
        {
            baseUrl = APIConfig.BASE_URL + "/" + endpoint;
        }

        protected IEnumerator GetAll(
            string endpoint,
            System.Action<T[]> onSuccess,
            System.Action<string> onError)
        {
            using (UnityWebRequest request = UnityWebRequest.Get(baseUrl+endpoint))
            {
                yield return request.SendWebRequest();

                if (request.result != UnityWebRequest.Result.Success)
                {
                    onError?.Invoke(request.error);
                }
                else
                {
                    T[] data = JsonConvert.DeserializeObject<T[]>(request.downloadHandler.text);
                    onSuccess?.Invoke(data);
                }
            }
        }

        protected IEnumerator GetById(
            string endpoint,
            System.Action<T> onSuccess,
            System.Action<string> onError)
        {
            using (UnityWebRequest request = UnityWebRequest.Get($"{baseUrl}/{endpoint}"))
            {
                yield return request.SendWebRequest();

                if (request.result != UnityWebRequest.Result.Success)
                    onError?.Invoke(request.error);
                else
                    onSuccess?.Invoke(
                        JsonConvert.DeserializeObject<T>(request.downloadHandler.text)
                    );
            }
        }

        protected IEnumerator Create(
            string endpoint,
            T item,
            System.Action<T> onSuccess,
            System.Action<string> onError)
        {
            string json = JsonConvert.SerializeObject(item);

            UnityWebRequest request = new UnityWebRequest(baseUrl+endpoint, "POST");
            request.uploadHandler = new UploadHandlerRaw(Encoding.UTF8.GetBytes(json));
            request.downloadHandler = new DownloadHandlerBuffer();
            request.SetRequestHeader("Content-Type", "application/json");

            yield return request.SendWebRequest();

            if (request.result != UnityWebRequest.Result.Success)
                onError?.Invoke(request.error);
            else
                onSuccess?.Invoke(
                    JsonConvert.DeserializeObject<T>(request.downloadHandler.text)
                );
        }

        protected IEnumerator Update(
            string endpoint,
            T item,
            System.Action<string> onSuccess,
            System.Action<string> onError)
        {
            string json = JsonConvert.SerializeObject(item);

            UnityWebRequest request = UnityWebRequest.Put($"{baseUrl}/{endpoint}", json);
            request.SetRequestHeader("Content-Type", "application/json");

            yield return request.SendWebRequest();

            if (request.result != UnityWebRequest.Result.Success)
                onError?.Invoke(request.error);
            else
                onSuccess?.Invoke("Updated");
        }

        protected IEnumerator Delete(
            string endpoint,
            System.Action<string> onSuccess,
            System.Action<string> onError)
        {
            UnityWebRequest request = UnityWebRequest.Delete($"{baseUrl}/{endpoint}");

            yield return request.SendWebRequest();

            if (request.result != UnityWebRequest.Result.Success)
                onError?.Invoke(request.error);
            else
                onSuccess?.Invoke("Deleted");
        }


    }
}

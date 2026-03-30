using Assets._Project.API.Config;
using Newtonsoft.Json;
 
using System.Text;
using UnityEngine;
using UnityEngine.Networking;



namespace Assets._Project.API.Interface
{
    public abstract class ApiService
    {

        protected readonly string baseUrl;

        protected ApiService(string endpoint)
        {
            baseUrl = APIConfig.BASE_URL + "api/" + endpoint;

        }

        protected async Awaitable<T> SendRequestAsync<T>(
            string endpoint,
            string method,
            string jsonBody = null
            )
        {


            using (var request = CreateRequest(baseUrl + endpoint, method, jsonBody))
            {

                Debug.Log("FINAL URL = " + baseUrl + endpoint);
                var operation = request.SendWebRequest();



                while (!operation.isDone)
                    await Awaitable.NextFrameAsync();

                if (request.result != UnityWebRequest.Result.Success)
                {
                    throw new System.Exception(
                        $"Error: {request.error}, Status Code: {request.responseCode}"
                    );
                }

                var responseText = request.downloadHandler.text;


                if (string.IsNullOrEmpty(responseText))
                    return default;



                return JsonConvert.DeserializeObject<T>(request.downloadHandler.text);
            }
        }


        protected UnityWebRequest CreateRequest(string url, string method, string jsonBody = null)
        {
            UnityWebRequest request = new UnityWebRequest(url, method);

            if (jsonBody != null)
            {
                var bytes = Encoding.UTF8.GetBytes(jsonBody);
                request.uploadHandler = new UploadHandlerRaw(bytes)
                {
                    contentType = "application/json"
                };
            }


            request.downloadHandler = new DownloadHandlerBuffer();
            request.timeout = APIConfig.Timeout;

            foreach (var header in APIConfig.DefaultHeaders)
            {

                request.SetRequestHeader(header.Key, header.Value);
            }

            return request;
        }


        protected Awaitable<T> GetAsync<T>(
            string endpoint)
        {
            return SendRequestAsync<T>(endpoint, "GET");
        }

        protected Awaitable<T[]> GetAllAsync<T>(
            string endpoint)
        {
            return SendRequestAsync<T[]>(endpoint, "GET");
        }

        protected Awaitable<T> CreateAsync<T>(
            string endpoint,
            T item)
        {

            string json = JsonConvert.SerializeObject(item);
            return SendRequestAsync<T>(endpoint, "POST", json);
        }

        protected Awaitable<T[]> CreateManyAsync<T>(
            string endpoint,
            T[] items)
        {
            string json = JsonConvert.SerializeObject(items);
            return SendRequestAsync<T[]>(endpoint, "POST", json);
        }

        protected Awaitable<T> UpdateAsync<T>(
            string endpoint,
            T item)
        {
            string json = JsonConvert.SerializeObject(item);
            return SendRequestAsync<T>(endpoint, "PUT", json);
        }

        protected Awaitable<T[]> UpadateManyAsync<T>(
            string endpoint,
            T[] items)
        {
            string json = JsonConvert.SerializeObject(items);
            return SendRequestAsync<T[]>(endpoint, "PUT", json);
        }

        protected Awaitable<string> DeleteAsync(
            string endpoint)
        {
            return SendRequestAsync<string>(endpoint, "DELETE");
        }

        protected Awaitable<string> DeleteManyAsync<T>(
            string endpoint,
            T[] item
            )
        {
            string json = JsonConvert.SerializeObject(item);
            return SendRequestAsync<string>(endpoint, "DELETE", json);
        }





        /**
        protected IEnumerator GetAll<T>(
          string endpoint,
          System.Action<T[]> onSuccess,
          System.Action<CatchError> onError)
        {
            using (var request =
                CreateRequest(baseUrl + endpoint, "GET"))
            {
                yield return request.SendWebRequest();

                if (request.result != UnityWebRequest.Result.Success)
                    onError?.Invoke(ParseError(request));
                else
                    onSuccess?.Invoke(
                        JsonConvert.DeserializeObject<T[]>(request.downloadHandler.text)
                    );
            }
        }


        protected IEnumerator GetById<T>(
            string endpoint,
            System.Action<T> onSuccess,
            System.Action<CatchError> onError)
        {
            using (var request =
                CreateRequest(baseUrl + endpoint, "GET"))
            {
                yield return request.SendWebRequest();

                if (request.result != UnityWebRequest.Result.Success)
                    onError?.Invoke(ParseError(request));
                else
                    onSuccess?.Invoke(
                        JsonConvert.DeserializeObject<T>(request.downloadHandler.text)
                    );
            }
        }

        protected IEnumerator Create<T>(
            string endpoint,
            T item,
            System.Action<T> onSuccess,
            System.Action<CatchError> onError)
        {
            string json = JsonConvert.SerializeObject(item);

            UnityWebRequest request = new UnityWebRequest(baseUrl + endpoint, "POST");
            request.uploadHandler = new UploadHandlerRaw(Encoding.UTF8.GetBytes(json));
            request.downloadHandler = new DownloadHandlerBuffer();
            request.SetRequestHeader("Content-Type", "application/json");

            yield return request.SendWebRequest();

            if (request.result != UnityWebRequest.Result.Success)
                onError?.Invoke(ParseError(request));
            else
                onSuccess?.Invoke(
                    JsonConvert.DeserializeObject<T>(request.downloadHandler.text)
                );
        }

        protected IEnumerator Create<T>(
    string endpoint,
    System.Action<T> onSuccess,
    System.Action<CatchError> onError)
        {
            using (var request =
                CreateRequest(baseUrl + endpoint, "POST"))
            {
                yield return request.SendWebRequest();

                if (request.result != UnityWebRequest.Result.Success)
                {
                    onError?.Invoke(ParseError(request));
                }
                else
                {
                    if (!string.IsNullOrEmpty(request.downloadHandler.text))
                    {
                        onSuccess?.Invoke(
                            JsonConvert.DeserializeObject<T>(request.downloadHandler.text)
                        );
                    }
                    else
                    {
                        onSuccess?.Invoke(default);
                    }
                }
            }
        }


        protected IEnumerator CreateMany<T>(
         string endpoint,
         T[] items,
         System.Action<T[]> onSuccess,
         System.Action<CatchError> onError)
        {
            string json = JsonConvert.SerializeObject(items);

            using (var request =
                CreateRequest(baseUrl + endpoint, "POST", json))
            {
                yield return request.SendWebRequest();

                if (request.result != UnityWebRequest.Result.Success)
                {
                    onError?.Invoke(ParseError(request));
                }
                else
                {
                    if (!string.IsNullOrEmpty(request.downloadHandler.text))
                    {
                        onSuccess?.Invoke(
                            JsonConvert.DeserializeObject<T[]>(request.downloadHandler.text)
                        );
                    }
                    else
                    {
                        onSuccess?.Invoke(null);
                    }
                }
            }
        }





        protected IEnumerator Update<T>(
        string endpoint,
        T item,
        System.Action<string> onSuccess,
        System.Action<CatchError> onError)
        {
            string json = JsonConvert.SerializeObject(item);

            using (var request =
                CreateRequest(baseUrl + endpoint, "PUT", json))
            {
                yield return request.SendWebRequest();

                if (request.result != UnityWebRequest.Result.Success)
                    onError?.Invoke(ParseError(request));
                else
                    onSuccess?.Invoke("Updated");
            }
        }




        protected IEnumerator Delete(
                string endpoint,
                System.Action<string> onSuccess,
                System.Action<CatchError> onError)
        {
            using (var request =
                CreateRequest(baseUrl + endpoint, "DELETE"))
            {
                yield return request.SendWebRequest();

                if (request.result != UnityWebRequest.Result.Success)
                    onError?.Invoke(ParseError(request));
                else
                    onSuccess?.Invoke("Deleted");
            }
        }

        protected CatchError ParseError(UnityWebRequest request)
        {
            try
            {
                return JsonConvert.DeserializeObject<CatchError>(
                    request.downloadHandler.text
                );
            }
            catch
            {
                return new CatchError
                {
                    Status = (int)request.responseCode,
                    Error = "Client Error",
                    Message = request.error,
                   Path = request.url,
               
                };
            }
        }

    }
        **/

    }
}

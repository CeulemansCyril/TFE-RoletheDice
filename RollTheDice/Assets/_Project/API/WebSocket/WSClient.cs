using NativeWebSocket;
using System;
using System.Text;
using UnityEngine;

namespace Assets._Project.API.WeSocket
{
    public class WSClient : MonoBehaviour
    {
        private WebSocket webSocket;

        public async void Connect(long gameId, long playerId)
        {
            webSocket = new WebSocket($"ws://localhost:8080/ws?gameId={gameId}&playerId={playerId}");

            webSocket.OnOpen += () =>
            {
                Debug.Log(" WebSocket connecté");
            };

            webSocket.OnError += (e) =>
            {
                Debug.LogError(" WebSocket erreur: " + e);
            };

            webSocket.OnClose += (e) =>
            {
                Debug.Log(" WebSocket fermé");
            };

            webSocket.OnMessage += (bytes) =>
            {
                string message = Encoding.UTF8.GetString(bytes);
                Debug.Log(" Reçu: " + message);

                HandleMessage(message);
            };

            await webSocket.Connect();
        }

        private void Update()
        {
            if (webSocket != null)
            {
                webSocket.DispatchMessageQueue();
            }
        }

        public async void Send(string json)
        {
            if (webSocket != null && webSocket.State == WebSocketState.Open)
            {
                await webSocket.SendText(json);
            }
        }

        private void HandleMessage(string json)
        {
      
            Debug.Log("Traitement message: " + json);
        }

        private async void OnApplicationQuit()
        {
            if (webSocket != null)
            {
                await webSocket.Close();
            }
        }
    }
}
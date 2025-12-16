using System.Collections.Generic;

namespace RollTheDice.API.Config
{
    public static class APIConfig
    {
        public const string BASE_URL = "https://localhost:8080/api";

        public static int Timeout = 10;

        public static Dictionary<string, string> DefaultHeaders =>
            new Dictionary<string, string>
            {
            { "Content-Type", "application/json" }
            };
    }
}
using System.Collections.Generic;

namespace Assets._Project.API.Config
{
    public static class APIConfig
    {
        public const string BASE_URL = "http://localhost:8080/";

        public static int Timeout = 10;

        public static Dictionary<string, string> DefaultHeaders =>
            new Dictionary<string, string>
            {
            { "Content-Type", "application/json" }
            };
    }
}
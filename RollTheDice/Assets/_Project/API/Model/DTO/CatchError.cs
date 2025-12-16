using System;

namespace RollTheDice.API.DTO
{
    public class CatchError
    {
        public DateTime Time { get; set; }
        public int Status { get; set; }
        public string Error { get; set; }
        public string Message { get; set; }
        public string Path { get; set; }

        public CatchError(){}
        public CatchError(DateTime time, int status, string error, string message, string path)
        {
            Time = time;
            Status = status;
            Error = error;
            Message = message;
            Path = path;
        }
    }
}
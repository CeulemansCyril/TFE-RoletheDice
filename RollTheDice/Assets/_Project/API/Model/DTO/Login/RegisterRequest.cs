using System;

namespace Assets._Project.API.Model.DTO.Login
{
    [Serializable]
    public class RegisterRequest
    {
        public string Username { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }

        public RegisterRequest(){}
        public RegisterRequest(string username, string password, string email){
            Username = username;
            Password = password;
            Email = email;
        }
    }
}
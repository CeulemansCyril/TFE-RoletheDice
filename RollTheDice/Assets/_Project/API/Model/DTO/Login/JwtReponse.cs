using Assets._Project.API.Enums;

namespace Assets._Project.API.Model.DTO.Login
{
    public class JwtReponse
    {
         public string Token{ get; set; }
        public string Type { get; set; }
        public RoleUser Role { get; set; }
        public long IdUser { get; set; }

        public JwtReponse(){}
        public JwtReponse(string token, string type, RoleUser role, long idUser)
        {
                Token = token;
                Type = type;
                Role = role;
                IdUser = idUser;
        }
    }
}
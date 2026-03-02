using Assets._Project.API.Enums;

namespace Assets._Project.API.Model.DTO.GameDTO.TokenDTO
{   
    public class TokenDTO
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public string ImageURL { get; set; }
        public TokenType Type { get; set; }
        public bool CanMove { get; set; }
        public long IdOwner { get; set; }
        public long IdFiche { get; set; }
        public long IdGameBundle { get; set; }

        public TokenDTO(){}
        public TokenDTO(long id, string name, string imageURL, TokenType type, bool canMove, long idOwner, long idFiche, long idGameBundle)
        {
            Id = id;
            Name = name;
            ImageURL = imageURL;
            Type = type;
            CanMove = canMove;
            IdOwner = idOwner;
            IdFiche = idFiche;
            IdGameBundle = idGameBundle;
        }
    }
}
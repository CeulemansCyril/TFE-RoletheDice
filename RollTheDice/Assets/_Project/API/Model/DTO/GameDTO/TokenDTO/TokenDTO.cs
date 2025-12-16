
using RollTheDice.API.Enums;

namespace RollTheDice.API.DTO.GameDTO.TokenDTO
{
    public class TokenDTO
    {
        public long Id { get; set; }
        private string Name { get; set; }
        private string ImageURL { get; set; }
        private TokenType Type { get; set; }
        private bool CanMove { get; set; }
        private long IdOwner { get; set; }
        private long IdFiche { get; set; }
        private long IdGameBundle { get; set; }

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
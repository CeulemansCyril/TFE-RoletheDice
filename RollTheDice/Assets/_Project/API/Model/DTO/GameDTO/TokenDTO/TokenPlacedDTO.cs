namespace RollTheDice.API.DTO.GameDTO.TokenDTO
{
    public class TokenPlacedDTO
    {
        public long Id { get; set; }
        public double PositionX { get; set; }
        public double PositionY { get; set; }
        public double Rotation { get; set; }
        public double Scale { get; set; }
        public bool SawByEveryone { get; set; }
        public long IdLayout { get; set; }
        public long IdToken { get; set; }

        public TokenPlacedDTO(){}
        public TokenPlacedDTO(long id, double positionX, double positionY, double rotation,double scale, bool sawByEveryone, long idLayout, long idToken)
        {
            Id = id;
            PositionX = positionX;
            PositionY = positionY;
            Rotation = rotation;
            Scale = scale;
            SawByEveryone = sawByEveryone;
            IdLayout = idLayout;
            IdToken = idToken;

        }         
    }
}
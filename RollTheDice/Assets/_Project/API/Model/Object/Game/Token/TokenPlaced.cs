namespace RollTheDice.API.Models.Game.Token
{
    public class TokenPlaced
    {
        public long Id { get; set; }
        public double PositionX { get; set; }
        public double PositionY { get; set; }
        public double Rotation { get; set; }
        public double Scale  { get; set; }
        public bool SawByEveryone { get; set; }

        public TokenPlaced(){}
        public TokenPlaced(long id, double positionX, double positionY, double rotation, double scale, bool sawByEveryone)
        {
            Id = id;
            PositionX = positionX;
            PositionY = positionY;
            Rotation = rotation;
            Scale = scale;
            SawByEveryone = sawByEveryone;
        }
    }
}
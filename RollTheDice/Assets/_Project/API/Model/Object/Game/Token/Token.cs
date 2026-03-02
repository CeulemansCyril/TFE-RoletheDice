using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.API.Enums;


namespace Assets._Project.API.Model.Object.Game.Token
{
    public class Tokens
    {
      public long Id  { get; set; }
      public string Name  { get; set; }
      public string ImageURL  { get; set; }
      public TokenType Type  { get; set; }
      public bool CanMove  { get; set; }
      public Players Owner { get; set; }
      public CustomObject CustomObject { get; set; }

      public Tokens(){}
      public Tokens(long id, string name, string imageURL, TokenType type, Players owner, CustomObject customObject)
        {
            Id = id;
            Name = name;
            ImageURL = imageURL;
            Type = type;
            Owner = owner;
            CustomObject = customObject;
        }

    }
}
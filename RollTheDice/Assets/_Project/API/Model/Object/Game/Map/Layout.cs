using System.Collections.Generic;
using RollTheDice.API.Models.Game.Token;

namespace RollTheDice.API.Models.Game.Map
{
    public class Layout
    {
            private long Id { get; set;}
            private string Name { get; set;}
             private int IndexZ { get; set;}
            private  string BackgroundImageURL { get; set;}
            private List<TokenPlaced> Tokens { get; set;}  = new List<TokenPlaced>();

        public Layout(){}
        public Layout(long id, string name, int indexZ, string backgroundImageURL, List<TokenPlaced> tokens)
        {
          Id = Id;   
          Name = name;
          IndexZ = indexZ;
          BackgroundImageURL = backgroundImageURL;
          Tokens = tokens;
        }
    }
}
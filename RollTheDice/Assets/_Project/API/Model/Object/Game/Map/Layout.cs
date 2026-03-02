using System.Collections.Generic;
using Assets._Project.API.Model.Object.Game.Token;

namespace Assets._Project.API.Model.Object.Game.Map
{
    public class Layout
    {
            public long Id { get; set;}
        public string Name { get; set;}
        public int IndexZ { get; set;}
        public string BackgroundImageURL { get; set;}
        public List<TokenPlaced> Tokens { get; set;}  = new List<TokenPlaced>();
        public long IdMap { get; set; }

        public Layout(){}
        public Layout(long id, string name, int indexZ, string backgroundImageURL, List<TokenPlaced> tokens,long idMap)
        {
          Id = Id;   
          Name = name;
          IndexZ = indexZ;
          BackgroundImageURL = backgroundImageURL;
          Tokens = tokens;
            IdMap = idMap;
        }
    }
}
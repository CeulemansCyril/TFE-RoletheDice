using System.Collections.Generic;

namespace Assets._Project.API.Model.DTO.GameDTO.MapDTO
{
    public class LayoutDTO
    {
        public long Id  { get; set;}
        public string Name { get; set; }
        public int IndexZ  { get; set;}
        public  string BackgroundImageURL  { get; set;}
        public long IdMap { get; set; }
        public List<long> IdTokenPlaced  { get; set;}

        public LayoutDTO(){}
        public LayoutDTO(long id, string name, int indexZ, string backgroundImageURL, long idMap,List<long> idTokenPlaced)
        {
            Id = id;
            Name = name;
            IndexZ = indexZ;
            BackgroundImageURL = backgroundImageURL;
            IdMap = idMap;
            IdTokenPlaced = idTokenPlaced;
        }
    }
}
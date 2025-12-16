using System.Collections.Generic;

namespace RollTheDice.API.DTO.GameDTO.MapDTO
{
    public class MapDTO
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public int Width { get; set; }
        public int Height { get; set; }
        public bool GridEnabled { get; set; } 
        public double CellSize { get; set; } 
        public string GridColor { get; set; }
        public double GridThickness { get; set; }
        public string GridType { get; set; }

        public List<long> IdGameBundle { get; set; } = new List<long>();
        public List<long> IdLayouts { get; set; } = new List<long>();

        public MapDTO(){}
        public MapDTO(long id, string name, string description, int width, int height, bool gridEnabled, double cellSize, string gridColor, double gridThickness, string gridType, List<long> idGameBundle, List <long> idLayout)
        {
            Id = id;
            Name = name;
            Description = description;
            Width = width;
            Height = height;
            GridEnabled = gridEnabled;
            CellSize = cellSize;
            GridColor = gridColor;
            GridThickness = gridThickness;
            GridType = gridType;
            IdGameBundle = idGameBundle;
            IdLayouts = idLayout;
        }
    }
}
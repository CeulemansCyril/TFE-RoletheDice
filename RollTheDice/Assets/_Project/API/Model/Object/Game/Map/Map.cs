using System.Collections.Generic;

namespace RollTheDice.API.Models.Game.Map
{
    public class Maps
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

        public List<Layout> Layouts { get; set; } = new List<Layout>();

        public Maps(){}
        public Maps(long id, string name,string description, int width,int height,bool gridEnabled,double cellSize, string gridColor,double gridThickness, string gridType,List<Layout> layouts)
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
            Layouts = layouts;
    
        } 

    }
}
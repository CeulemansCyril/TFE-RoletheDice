namespace Assets._Project.API.Model.Object.Game.Templates
{
    public class TemplateField
    {
        public long Id { get; set;}
        public string Label { get; set; }
        public string Type { get; set;}
        public bool Required { get; set;}
        public double MinValue { get; set;}
        public double MaxValue { get; set;}

        public double Width { get; set; }
        public double Height { get; set; }

        public double PositionX { get; set;}
        public double PositionY { get; set;}
        public OptionList OptionList { get; set;}

        public TemplateField(){}
        public TemplateField(long id,string label, string type,bool required, double minValue,double maxValue,double positionX,double positionY, OptionList optionList,double width,double height)
        {
            Id = id;
            Label = label;
            Type = type;
            Required = required;
            MinValue = minValue;
            MaxValue = maxValue;
            PositionX = positionX;
            PositionY = positionY;
            OptionList = optionList;
            Width = width;
            Height = height;
        } 

    }
}
namespace Assets._Project.API.Model.Object.Game.Money
{
    public class Currency
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public string Symbol { get; set; }
        public string Code { get; set; }
        public int BaseUnit { get; set; }

        public Currency(){}
        public Currency(long id, string name,   string symbol, string code, int baseUnit)
        {
            Id = id;
            Name = name;
            Symbol = symbol;
            Code = code;
            BaseUnit = baseUnit;
        }
    }
}
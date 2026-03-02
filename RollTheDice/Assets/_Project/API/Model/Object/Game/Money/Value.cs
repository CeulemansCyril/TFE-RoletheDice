namespace Assets._Project.API.Model.Object.Game.Money
{
    public class Value
    {
        public double Amount { get; set; }
        public Currency Currency { get; set; }

        public Value(){}
        public Value(double amount, Currency currency)
        {
            Amount = amount;
            Currency = currency;
        }
    }
}
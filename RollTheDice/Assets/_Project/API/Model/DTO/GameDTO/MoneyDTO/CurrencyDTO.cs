using System.Collections.Generic;

namespace Assets._Project.API.Model.DTO.GameDTO.MoneyDTO
{
    public class CurrencyDTO
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public string Symbol { get; set; }
        public string Code { get; set; }
        public int BaseUnit { get; set; }
        public List<long>IdGameBundle { get; set; }

        public CurrencyDTO(){}
        public CurrencyDTO(long id, string name, string symbol, int baseUnit, List<long> idGameBundle)
        {
            Id = id;
            Name = name;
            Symbol = symbol;
            BaseUnit = baseUnit;
            IdGameBundle = idGameBundle;
            
        }
    }
}
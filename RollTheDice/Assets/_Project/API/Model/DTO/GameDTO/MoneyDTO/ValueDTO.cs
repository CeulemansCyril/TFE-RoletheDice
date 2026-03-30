using UnityEngine;
using System.Collections;

namespace Assets._Project.API.Model.DTO.GameDTO.MoneyDTO
{
	public class ValueDTO 
	{
		public  double Amount { get; set; }
		public long? CurrencyId { get; set; }

		public ValueDTO(double amount, long currencyId)
		{
			Amount = amount;
            CurrencyId = currencyId;
        }

        public ValueDTO()
        {
     
        }
    }
}
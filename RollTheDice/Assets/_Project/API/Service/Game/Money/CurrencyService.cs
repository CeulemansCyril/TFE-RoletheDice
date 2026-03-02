using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.GameDTO.MoneyDTO;
using Assets._Project.API.Model.Object.Game.Money;
using System.Collections;
using UnityEngine;

namespace Assets._Project.API.Service.Game.Money
{
    public class CurrencyService : ApiService
    {
        private CatchError onError;
        public CurrencyService(string endpoint) : base("currency"){}

        public Awaitable<CurrencyDTO> CreateCurrencyAsync<CurrencyDTO>(CurrencyDTO currency, long idUser)
        {
           return CreateAsync("/CreateCurrency/" + idUser, currency);
        }
        
        public Awaitable<CurrencyDTO> UpdateCurrency<CurrencyDTO>(CurrencyDTO currency)
        {
            return UpdateAsync("/UpdateCurrency ", currency);
        }

        public Awaitable<string> DeleteCurrency(long id)
        {
            return DeleteAsync("/DeleteCurrency/" + id);
        }
        
        public Awaitable<CurrencyDTO[]> GetAllCurrenciesByGameBundleId<CurrencyDTO>(long id)
        {
            return GetAllAsync<CurrencyDTO>("/GetAllCurrenciesByGameBundleId/" + id);
        }

 

        public Currency CurrencyDTOToCurrency(CurrencyDTO currencyDTO)
        {
            Currency currency = new Currency();
            currency.Id = currencyDTO.Id;
            currency.Name = currencyDTO.Name;
            currency.Symbol = currencyDTO.Symbol;
            currency.Code = currencyDTO.Code;
            currency.BaseUnit = currencyDTO.BaseUnit;
            return currency;
        }

        public CurrencyDTO CurrencyToCurrencyDTO(Currency currency)
        {
            CurrencyDTO currencyDTO = new CurrencyDTO();
            currencyDTO.Id = currency.Id;
            currencyDTO.Name = currency.Name;
            currencyDTO.Symbol = currency.Symbol;
            currencyDTO.Code = currency.Code;
            currencyDTO.BaseUnit = currency.BaseUnit;
            return currencyDTO;
        }
    }

}
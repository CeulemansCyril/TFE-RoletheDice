using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.GameDTO.MoneyDTO;
using Assets._Project.API.Model.Object.Game.Money;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Unity.VisualScripting;
using UnityEditor.Experimental.GraphView;
using UnityEngine;

namespace Assets._Project.API.Service.Game.Money
{
    public class CurrencyService : ApiService
    {
        
        public CurrencyService() : base("currency"){}

        public Awaitable<CurrencyDTO> CreateCurrencyAsync (CurrencyDTO currency, long idUser)
        {
           return CreateAsync("/CreateCurrency/" + idUser, currency);
        }

        public Awaitable<CurrencyDTO[]>CreateManyCurrencyAsync(CurrencyDTO[] currency, long idUser)
        {
            return CreateManyAsync("/CreateManyCurrency/"+idUser, currency);
        }
        
        public Awaitable<CurrencyDTO> UpdateCurrency (CurrencyDTO currency)
        {
            return UpdateAsync("/UpdateCurrency", currency);
        }

        public Awaitable<CurrencyDTO[]> UpdateManyCurrency(CurrencyDTO[] currencyDTOs)
        {
            return UpadateManyAsync("/UpdateManyCurrency", currencyDTOs);
        }

        public Awaitable<string> DeleteCurrency(long id)
        {
            return DeleteAsync("/DeleteCurrency/" + id);
        }
        
        public Awaitable<CurrencyDTO[]> GetAllCurrenciesByGameBundleId (long id)
        {
            return GetAllAsync<CurrencyDTO>("/GetAllCurrenciesByGameBundleId/" + id);
        }


        public async Task<Currency[]> SaveAllCurrencies(CurrencyDTO[] currencies, long idUser)
        {
            List<CurrencyDTO> create = currencies.Where(c => c.Id <= 0).ToList();
            List<CurrencyDTO> update = currencies.Where(c => c.Id > 0).ToList();

            if (create.Count > 0)
                create = (await CreateManyCurrencyAsync(create.ToArray(), idUser)).ToList();

            if (update.Count > 0)
                update = (await UpdateManyCurrency(update.ToArray())).ToList();

            List<CurrencyDTO> all = new List<CurrencyDTO>();
            all.AddRange(create);
            all.AddRange(update);

            return all.Select(CurrencyDTOToCurrency).ToArray();
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

        public CurrencyDTO CurrencyToCurrencyDTO(Currency currency, long idBundle)
        {
            CurrencyDTO currencyDTO = new CurrencyDTO();
            currencyDTO.Id = currency.Id;
            currencyDTO.Name = currency.Name;
            currencyDTO.Symbol = currency.Symbol;
            currencyDTO.Code = currency.Code;
            currencyDTO.BaseUnit = currency.BaseUnit;
            currencyDTO.IdGameBundle = idBundle;
            return currencyDTO;
        }
    }

}
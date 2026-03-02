using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.GameDTO;
using Assets._Project.API.Model.Object.Game;
using Assets._Project.API.Model.Object.Game.Book;
using Assets._Project.API.Model.Object.Game.LootTable;
using Assets._Project.API.Model.Object.Game.Map;
using Assets._Project.API.Model.Object.Game.Money;
using Assets._Project.API.Model.Object.Game.Token;
using Assets._Project.API.Model.Object.User;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;


namespace Assets._Project.API.Service.Game
{
    public class GameBundleService : ApiService
    {
        private CatchError onError;
        public GameBundleService() : base("gameBundle") { }

        public Awaitable<GameBundleDTO> CreateGameBundle<GameBundleDTO>(GameBundleDTO gameBundle, long idUser)
        {
            return CreateAsync("/CreateGameBundle/" + idUser, gameBundle);
        }

        public Awaitable<GameBundleDTO> UpdateGameBundle<GameBundleDTO>(GameBundleDTO gameBundle)
        {
            return UpdateAsync("/UpdateGameBundle ", gameBundle);
        }

       public Awaitable<string> DeleteGameBundle (long id)
        {
            return DeleteAsync("/DeleteGameBundle/" + id);
        } 

        public Awaitable<GameBundleDTO> GetGameBundleById<GameBundleDTO>(long id)
        {
            return GetAsync<GameBundleDTO>("/GetGameBundleById/" + id);
        }

         

        public GameBundle GameBundleDTOToGameBundle(GameBundleDTO gameBundleDTO)
        {
            GameBundle gameBundle = new GameBundle();
            gameBundle.Id = gameBundleDTO.Id;
            gameBundle.Name = gameBundleDTO.Name;
            gameBundle.Creator = new Users { Id = gameBundleDTO.IdCreator };
            gameBundle.Tokens = new List<Tokens>();

            foreach (long tokenId in gameBundleDTO.IdTokens)
            {
                gameBundle.Tokens.Add(new Tokens { Id = tokenId });
            }

            gameBundle.Books = new List<Books>();

            foreach (long bookId in gameBundleDTO.IdBooks)
            {
                gameBundle.Books.Add(new Books { Id = bookId });
            }

            gameBundle.Currencies = new List<Currency>();
            foreach (long currencyId in gameBundleDTO.IdCurrencies)
            {
                gameBundle.Currencies.Add(new Currency { Id = currencyId });
            }

            gameBundle.LootTables = new List<LootTables>();
            foreach (long lootTableId in gameBundleDTO.IdLootTables)
            {
                gameBundle.LootTables.Add(new LootTables { Id = lootTableId });
            }

            gameBundle.Maps = new List<Maps>();

            foreach (long mapId in gameBundleDTO.IdMap)
            {
                gameBundle.Maps.Add(new Maps { Id = mapId });
            }

            return gameBundle;
        }


        public GameBundleDTO GameBundleToGameBundleDTO(GameBundle gameBundle)
        {
            GameBundleDTO gameBundleDTO = new GameBundleDTO();
            gameBundleDTO.Id = gameBundle.Id;
            gameBundleDTO.Name = gameBundle.Name;
            gameBundleDTO.IdCreator = gameBundle.Creator.Id;
            gameBundleDTO.IdTokens = new List<long>();
            foreach (Tokens token in gameBundle.Tokens)
            {
                gameBundleDTO.IdTokens.Add(token.Id);
            }
            gameBundleDTO.IdBooks = new List<long>();
            foreach (Books book in gameBundle.Books)
            {
                gameBundleDTO.IdBooks.Add(book.Id);
            }
            gameBundleDTO.IdCurrencies = new List<long>();
            foreach (Currency currency in gameBundle.Currencies)
            {
                gameBundleDTO.IdCurrencies.Add(currency.Id);
            }
            gameBundleDTO.IdLootTables = new List<long>();
            foreach (LootTables lootTable in gameBundle.LootTables)
            {
                gameBundleDTO.IdLootTables.Add(lootTable.Id);
            }
            gameBundleDTO.IdMap = new List<long>();
            foreach (Maps map in gameBundle.Maps)
            {
                gameBundleDTO.IdMap.Add(map.Id);
            }
            return gameBundleDTO;
        }

    }
}

using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.GameDTO;
using Assets._Project.API.Model.Object.Game;
using Assets._Project.API.Model.Object.Game.Book;
using Assets._Project.API.Model.Object.Game.Storage;
using Assets._Project.API.Model.Object.Game.Templates;
using Assets._Project.API.Model.Object.User;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using UnityEngine;

namespace Assets._Project.API.Service.Game
{
    public class PlayerService : ApiService
    {
        private CatchError onError;
        public PlayerService() : base("player") { }

        public Awaitable<PlayersDTO>CreatePlayer<PlayerDTO>(PlayersDTO players) { 
        
            return CreateAsync("/createPlayer", players);
        }
        
        public Awaitable<PlayersDTO> UpdatePlayer<PlayerDTO>(PlayersDTO players)
        {
            return UpdateAsync("/updatePlayer ", players);
        }

        public Awaitable<string> DeletePlayer(long id)
        {
            return DeleteAsync("/deletePlayer/" + id);
        }


        public Awaitable<PlayersDTO> GetPlayerById <PlayersDTO>(long id)
        {
            return GetAsync<PlayersDTO>("/getPlayerById/" + id);
        }

        public Awaitable<PlayersDTO[]> GetPlayersByIdGame<PlayersDTO>(long id)
        {
            return GetAllAsync<PlayersDTO>("/getPlayersByIdGame" + id);
        }

        public Awaitable<PlayersDTO[]> GetPlayersByIdUser<PlayersDTO>(long id)
        {
            return GetAllAsync<PlayersDTO>("/getPlayersByIdUser/" + id);
        }

        

        public Players PlayersDTOToPlayers(PlayersDTO playersDTO)
        {
            Players players = new Players();
            players.Id = playersDTO.Id;
            players.Name = playersDTO.Name;
            players.LoreBook = new Books() { Id = playersDTO.IdLoreBook };
            players.Storages = new List<Storages>();
            foreach (long storageId in playersDTO.IdStorages)
            {
                players.Storages.Add(new Storages() { Id = storageId });
            }

            players.CharacterModel = new CustomObject() { Id = playersDTO.IdCharacterModel };
            players.User = new Users() { Id = playersDTO.IdUser };
            return players;
        }

        public PlayersDTO PlayersToPlayersDTO(Players players)
        {
            PlayersDTO playersDTO = new PlayersDTO();
            playersDTO.Id = players.Id;
            playersDTO.Name = players.Name;
            playersDTO.IdLoreBook = players.LoreBook != null ? players.LoreBook.Id : 0;
            List<long> storageIds = new List<long>();
            if (players.Storages != null)
            {
                foreach (Storages storage in players.Storages)
                {
                    storageIds.Add(storage.Id);
                }
            }
            playersDTO.IdStorages = storageIds;

            playersDTO.IdCharacterModel = players.CharacterModel != null ? players.CharacterModel.Id : 0;

            playersDTO.IdUser = players.User != null ? players.User.Id : 0;
            return playersDTO;
        }
    }
}

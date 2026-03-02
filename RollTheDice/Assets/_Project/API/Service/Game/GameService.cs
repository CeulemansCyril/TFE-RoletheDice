using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.GameDTO;
using Assets._Project.API.Model.Object.Chat;
using Assets._Project.API.Model.Object.Game;
using Assets._Project.API.Model.Object.Game.Book;
using Assets._Project.API.Model.Object.Game.Map;
using Assets._Project.API.Model.Object.User;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using UnityEditor.PackageManager;
using UnityEngine;

namespace Assets._Project.API.Service.Game
{
    public  class GameService : ApiService
    {
        private CatchError onError;
        public GameService() : base("game") { }

        public Awaitable<GameDTO> CreateGameAsync<GameDTO>(GameDTO game)
        {
            return CreateAsync("/CreateGame", game);
        }

        public Awaitable<GameDTO> UpdateGame<GameDTO>(GameDTO game)
        {
            return UpdateAsync("/UpdateGame ", game);
        }

        public Awaitable<string> DeleteGame (long id)
        {
            return DeleteAsync("/DeleteGame/" + id);
        }

        public Awaitable<GameDTO> GetGameById(long id)
        {
         return GetAsync<GameDTO>("/GetGameById/" + id);

        }
 

        public Games GameDTOToGame(GameDTO gameDTO)
        {
            Games game = new Games();
            game.Id = gameDTO.Id;
            game.Name = gameDTO.Name;
            game.Creator = new Users { Id = gameDTO.IdCreator };
            game.Players = new List<Players>();
            foreach (var playerId in gameDTO.IdPlayers)
            {
                game.Players.Add(new Players { Id = playerId });
            }
            game.PlayAdmins = new List<Users>();
            foreach (var adminId in gameDTO.IdPlayAdmins)
            {
                game.PlayAdmins.Add(new Users { Id = adminId });
            }

            game.ChatChanels = new List<ChatChanel>();
            foreach (var chatChanelId in gameDTO.IdChatChanels)
            {
                game.ChatChanels.Add(new ChatChanel { Id = chatChanelId });
            }
            game.Books = new List<Books>();
            foreach (var bookId in gameDTO.IdBooks)
            {
                game.Books.Add(new Books { Id = bookId });
            }
            game.ActiveMap = new Maps { Id = gameDTO.IdActiveMap };

            return game;
        }

        public GameDTO GameToGameDTO(Games game)
        {
            GameDTO gameDTO = new GameDTO();
            gameDTO.Id = game.Id;
            gameDTO.Name = game.Name;
            gameDTO.IdCreator = game.Creator.Id;
            gameDTO.IdPlayers = new List<long>();
            foreach (var player in game.Players)
            {
                gameDTO.IdPlayers.Add(player.Id);
            }
            gameDTO.IdPlayAdmins = new List<long>();
            foreach (var admin in game.PlayAdmins)
            {
                gameDTO.IdPlayAdmins.Add(admin.Id);
            }
            gameDTO.IdChatChanels = new List<long>();
            foreach (var chatChanel in game.ChatChanels)
            {
                gameDTO.IdChatChanels.Add(chatChanel.Id);
            }
            gameDTO.IdBooks = new List<long>();
            foreach (var book in game.Books)
            {
                gameDTO.IdBooks.Add(book.Id);
            }
            gameDTO.IdActiveMap = game.ActiveMap.Id;
            return gameDTO;
        }

    }
}

using System.Collections.Generic;
using RollTheDice.API.Models.Game.Template;
using RollTheDice.API.Models.Users;
using RollTheDice.API.Models.Game.Storages;
using RollTheDice.API.Models.Game.Books;

namespace RollTheDice.API.Models.Game
{
    public class Players
    {
        private long Id { get; set; }
        private string Name { get; set; }
        private Book LoreBook { get; set; }
        private List<Storage> Storages { get; set; }
        private CustomObject CharacterModel { get; set; }
         private User User { get; set; }

         public Players(){}
         public Players(long id, string name, Book loreBook,  List<Storage> storages,CustomObject characterModel,User user)
        {
            Id = id;
            Name = name;
            LoreBook = loreBook;
            Storages = storages;
            CharacterModel = characterModel;
            User = user;
        }
    }
}
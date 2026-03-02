using System.Collections.Generic;
using Assets._Project.API.Model.Object.Game.Storage;
using Assets._Project.API.Model.Object.User;
using Assets._Project.API.Model.Object.Game.Book;
using Assets._Project.API.Model.Object.Game.Templates;

namespace Assets._Project.API.Model.Object.Game
{
    public class Players
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public Books LoreBook { get; set; }
        public List<Storages> Storages { get; set; }
        public CustomObject CharacterModel { get; set; }
        public Users User { get; set; }

         public Players(){}
         public Players(long id, string name, Books loreBook,  List<Storages> storages,CustomObject characterModel,Users user)
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
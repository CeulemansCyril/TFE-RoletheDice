using System.Collections.Generic;

namespace RollTheDice.API.Models.Game.Storages
{
    public class Storage
    {
         public long Id { get; set;}
        public string Name { get; set;}
        public string Description { get; set;}
        public List<StorageItem> Items { get; set;}

        public Storage(){}
        public Storage(long id, string name, string description, List<StorageItem> items)
        {
            Id = id;
            Name = name;
            Description = description;
            Items = items;
        }

    }
}
 
using System.Collections.Generic;

namespace Assets._Project.API.Model.Object.Game.Storage
{
    public class Storages
    {
         public long Id { get; set;}
        public string Name { get; set;}
        public string Description { get; set;}
        public List<StorageItem> Items { get; set;}

        public Storages(){}
        public Storages(long id, string name, string description, List<StorageItem> items)
        {
            Id = id;
            Name = name;
            Description = description;
            Items = items;
        }

    }
}
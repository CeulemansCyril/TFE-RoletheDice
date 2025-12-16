using RollTheDice.API.Models.Game.Template;

namespace RollTheDice.API.Models.Game.Storages
{
    public class StorageItem
    {
        private long Id { get; set;}
        private CustomObject CustomObject { get; set;}
        private int Quantity { get; set;}

        public StorageItem(){}
        public StorageItem(long id, int quantity, CustomObject customObject)
        {
            Id = id;
            CustomObject = customObject;
            Quantity = quantity;
        }
    }
}
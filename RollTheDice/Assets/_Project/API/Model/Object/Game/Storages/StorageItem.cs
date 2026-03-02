using Assets._Project.API.Model.Object.Game.Templates;

namespace Assets._Project.API.Model.Object.Game.Storage
{
    public class StorageItem
    {
        public long Id { get; set;}
        public CustomObject CustomObject { get; set;}
        public int Quantity { get; set;}

        public long IdStorage { get; set; }

        public StorageItem(){}
        public StorageItem(long id, int quantity, CustomObject customObject, long idStorage)
        {
            Id = id;
            CustomObject = customObject;
            Quantity = quantity;
            IdStorage = idStorage;
        }
    }
}
namespace RollTheDice.API.DTO.GameDTO.StorageDTO
{
        public class StorageItemDTO{
        private long Id { get; set;}
        private long IdCustomObject { get; set;}
        private int Quantity { get; set;}
        private long IdStorage { get; set;}

        public StorageItemDTO(){}
        public StorageItemDTO(long id, long idCustomObject, int quantity, long idStorage)
        {
            Id = id;
            IdCustomObject = idCustomObject;
            Quantity = quantity;
            IdStorage = idStorage;
        }
    }
}
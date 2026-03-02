namespace Assets._Project.API.Model.DTO.GameDTO.StorageDTO
{
        public class StorageItemDTO{
        public long Id { get; set;}
        public long IdCustomObject { get; set;}
        public int Quantity { get; set;}
        public long IdStorage { get; set;}

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
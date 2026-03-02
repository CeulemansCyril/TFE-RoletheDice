using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.GameDTO.StorageDTO;
using Assets._Project.API.Model.Object.Game.Storage;
using Assets._Project.API.Model.Object.Game.Templates;
using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;

namespace Assets._Project.API.Service.Game.Storage
{
    public class StorageService: ApiService
    {
        private CatchError onError;
        public StorageService() : base("storage") { }

        public Awaitable<StorageDTO> CreateStorage<StorageDTO>(StorageDTO storage)
        {
            return CreateAsync("/CreateStorage", storage);
        }
       public Awaitable<StorageDTO> UpdateStorage<StorageDTO>(StorageDTO book)
        {
            return UpdateAsync("/UpdateStorage ", book);
        }

        public Awaitable<string> DeleteStorage(long id)
        {
            return DeleteAsync("/DeleteStorage/" + id);
        }

        public Awaitable<StorageDTO> GetStorageById<StorageDTO>(long id)
        {
            return GetAsync<StorageDTO>("/GetStorageById/" + id);
        }

        public Awaitable<StorageDTO[]> GetAllStorageByIdGame<StorageDTO>(long id)
        {
            return GetAllAsync<StorageDTO>("/GetAllStorageByIdGame/" + id);
        }

        


        public Storages StorageDTOToEntity(StorageDTO storageDTO)
        {
            Storages storage = new Storages();
            storage.Id = storageDTO.Id;
            storage.Name = storageDTO.Name;
            storage.Description = storageDTO.Description;
            storage.Items = new List<StorageItem>();
            foreach (long itemId in storageDTO.IdItems)
            {
                StorageItem storageItem = new StorageItem();
                storageItem.Id = itemId;
                storage.Items.Add(storageItem);
            }

            return storage;
        }

        public StorageDTO StorageEntityToDTO(Storages storage)
        {
            StorageDTO storageDTO = new StorageDTO();
            storageDTO.Id = storage.Id;
            storageDTO.Name = storage.Name;
            storageDTO.Description = storage.Description;
            List<long> itemIds = new List<long>();
            foreach (StorageItem item in storage.Items)
            {
                itemIds.Add(item.Id);
            }
            storageDTO.IdItems = itemIds;
            return storageDTO;
        }

        // ------------------- StorageItem -------------------------
        public Awaitable<StorageItemDTO> CreateStorageItem<StorageItemDTO>(StorageItemDTO storage)
        {
            return CreateAsync("/CreateStorageItem", storage);
        }
       public Awaitable<StorageItemDTO> UpdateStorageItem<StorageItemDTO>(StorageItemDTO book)
        {
            return UpdateAsync("/UpdateStorageItem ", book);
        }

        public Awaitable<string> DeleteStorageItem(long id)
        {
            return DeleteAsync("/DeleteStorageItem/" + id);
        }


        public Awaitable<StorageItemDTO> GetStorageItemById<StorageItemDTO>(long id)
        {
            return GetAsync<StorageItemDTO>("/GetStorageItemById/" + id);
        }
       public Awaitable<StorageItemDTO[]> GetAllStorageItemsByIdStorage<StorageItemDTO>(long id)
        {
            return GetAllAsync<StorageItemDTO>("/GetAllStorageItemsByIdStorage/" + id);
        }

         

        public StorageItem StorageItemDTOToEntity(StorageItemDTO storageItemDTO)
        {
            StorageItem storageItem = new StorageItem();
            storageItem.Id = storageItemDTO.Id;
            storageItem.Quantity = storageItemDTO.Quantity;
            storageItem.IdStorage = storageItemDTO.IdStorage;
            storageItem.CustomObject = new CustomObject() { Id = storageItemDTO.IdCustomObject };
            return storageItem;
        }

        public StorageItemDTO StorageItemEntityToDTO(StorageItem storageItem)
        {
            StorageItemDTO storageItemDTO = new StorageItemDTO();
            storageItemDTO.Id = storageItem.Id;
            storageItemDTO.Quantity = storageItem.Quantity;
            storageItemDTO.IdStorage = storageItem.IdStorage;
            storageItemDTO.IdCustomObject = storageItem.CustomObject.Id;
            return storageItemDTO;
        }
    }
}

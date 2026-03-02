using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.GameDTO.MapDTO;
using Assets._Project.API.Model.Object.Game.Map;
using Assets._Project.API.Model.Object.Game.Token;
using System.Collections;
using UnityEngine;

namespace Assets._Project.API.Service.Game.Map
{
    public class MapService : ApiService
    {
        private CatchError onError;
        public MapService (string endpoint) : base ("map"){}

        // ------------------- Map -------------------------
        public Awaitable<MapDTO> CreateMap<MapDTO>(MapDTO map, long idUser)
        {
            return CreateAsync("/CreateMap/" + idUser, map);
        }
        
        public Awaitable<MapDTO> UpdateMap<MapDTO>(MapDTO map)
        {
            return UpdateAsync("/UpdateMap ", map);
        }

        public Awaitable<string> DeleteMap(long id)
        {
            return DeleteAsync("/DeleteMap/" + id);
        }
         public Awaitable<MapDTO[]> GetMapAllMapByGameBundleId <MapDTO>(long id)
        {
            return GetAllAsync<MapDTO>("/GetMapAllMapByGameBundleId/"+id);
        }

        public Maps MapsDTOToEntity(MapDTO mapDTO)
        {
            Maps map = new Maps();
            map.Id = mapDTO.Id;
            map.Name = mapDTO.Name;
            map.Description = mapDTO.Description;
     
            map.Width = mapDTO.Width;
            map.Height = mapDTO.Height;
            map.GridEnabled = mapDTO.GridEnabled;
            map.CellSize = mapDTO.CellSize;
            map.GridColor = mapDTO.GridColor;
            map.GridThickness = mapDTO.GridThickness;
            map.GridType = mapDTO.GridType;

            map.IdGameBundle = mapDTO.IdGameBundle;
            return map;
        }

        public MapDTO MapsEntityToDTO(Maps map)
        {
            MapDTO mapDTO = new MapDTO();
            mapDTO.Id = map.Id;
            mapDTO.Name = map.Name;
            mapDTO.Description = map.Description;
     
            mapDTO.Width = map.Width;
            mapDTO.Height = map.Height;
            mapDTO.GridEnabled = map.GridEnabled;
            mapDTO.CellSize = map.CellSize;
            mapDTO.GridColor = map.GridColor;
            mapDTO.GridThickness = map.GridThickness;
            mapDTO.GridType = map.GridType;
            mapDTO.IdGameBundle = map.IdGameBundle;
            return mapDTO;
        }

        // ------------------- Layout -------------------------
        public Awaitable<LayoutDTO> CreateLayout<LayoutDTO>(LayoutDTO layout)
        {
            return CreateAsync("/CreateLayout", layout);
        }

       public Awaitable<LayoutDTO> UpdateLayout <LayoutDTO>(LayoutDTO layout)
        {
            return UpdateAsync("/UpdateLayout ", layout);
        }

        public Awaitable<string> DeleteLayout(long id)
        {
            return DeleteAsync("/DeleteLayout/" + id);
        }
         public Awaitable<LayoutDTO[]> GetAllLayoutsByMapId <LayoutDTO>(long id)
        {
            return GetAllAsync<LayoutDTO>("/GetAllLayoutsByMapId/"+id);
        }
  

        public Layout LayoutDTOToEntity(LayoutDTO layoutDTO)
        {
            Layout layout = new Layout();
            layout.Id = layoutDTO.Id;
            layout.Name = layoutDTO.Name;
            layout.BackgroundImageURL = layoutDTO.BackgroundImageURL;
            layout.IndexZ = layoutDTO.IndexZ;
            layout.IdMap = layoutDTO.IdMap;

            foreach (long idTokenPlaced in layoutDTO.IdTokenPlaced)
            {
                TokenPlaced tokenPlaced = new TokenPlaced();
                tokenPlaced.Id = idTokenPlaced;
                layout.Tokens.Add(tokenPlaced);
            }



            return layout;
        }

        public LayoutDTO LayoutEntityToDTO(Layout layout)
        {
            LayoutDTO layoutDTO = new LayoutDTO();
            layoutDTO.Id = layout.Id;
            layoutDTO.Name = layout.Name;
            layoutDTO.BackgroundImageURL = layout.BackgroundImageURL;
            layoutDTO.IndexZ = layout.IndexZ;
            layoutDTO.IdMap = layout.IdMap;
            foreach (TokenPlaced tokenPlaced in layout.Tokens)
            {
                layoutDTO.IdTokenPlaced.Add(tokenPlaced.Id);
            }
            return layoutDTO;
        }
    }
}
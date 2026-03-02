using System.Collections.Generic;
using Assets._Project.API.Model.DTO.GameDTO.MapDTO;
using Assets._Project.API.Model.DTO.GameDTO.TokenDTO;

namespace Assets._Project.API.Model.DTO.ReponseDTO
{
    public class LayoutResponse
    {
        public LayoutDTO LayoutDTO { get; set; }
        public List<TokenPlacedDTO> TokenPlacedDTOs { get; set; }

        public LayoutResponse(){}
        public LayoutResponse(LayoutDTO layoutDTO, List<TokenPlacedDTO> tokenPlaced)
        {
                LayoutDTO = layoutDTO;
                TokenPlacedDTOs = tokenPlaced;
        }
    }
}
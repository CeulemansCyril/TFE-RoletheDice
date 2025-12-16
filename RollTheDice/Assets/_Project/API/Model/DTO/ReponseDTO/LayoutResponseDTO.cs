using System.Collections.Generic;
using RollTheDice.API.DTO.GameDTO.MapDTO;
using RollTheDice.API.DTO.GameDTO.TokenDTO;

namespace RollTheDice.API.DTO.ReponseDTO
{
    public class LayoutResponseDTO
    {
        public LayoutDTO LayoutDTO { get; set; }
        public List<TokenPlacedDTO> TokenPlacedDTOs { get; set; }

        public LayoutResponseDTO(){}
        public LayoutResponseDTO(LayoutDTO layoutDTO, List<TokenPlacedDTO> tokenPlaced)
        {
                LayoutDTO = layoutDTO;
                TokenPlacedDTOs = tokenPlaced;
        }
    }
}
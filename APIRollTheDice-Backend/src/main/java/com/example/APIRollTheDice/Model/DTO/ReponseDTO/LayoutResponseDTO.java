package com.example.APIRollTheDice.Model.DTO.ReponseDTO;

import com.example.APIRollTheDice.Model.DTO.GameDTO.MapDTO.LayoutDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.TokenDTO.TokenPlacedDTO;



import java.util.ArrayList;
import java.util.List;


public class LayoutResponseDTO {
    private LayoutDTO layoutDTO;
    private List<TokenPlacedDTO> tokenPlacedDTOs = new ArrayList<>();

    public LayoutResponseDTO( LayoutDTO layoutDTO,List<TokenPlacedDTO> tokenPlacedDTOs) {
        this.tokenPlacedDTOs = tokenPlacedDTOs;
        this.layoutDTO = layoutDTO;
    }

    public LayoutResponseDTO() {
    }

    public LayoutDTO getLayoutDTO() {
        return layoutDTO;
    }

    public void setLayoutDTO(LayoutDTO layoutDTO) {
        this.layoutDTO = layoutDTO;
    }

    public List<TokenPlacedDTO> getTokenPlacedDTOs() {
        return tokenPlacedDTOs;
    }

    public void setTokenPlacedDTOs(List<TokenPlacedDTO> tokenPlacedDTOs) {
        this.tokenPlacedDTOs = tokenPlacedDTOs;
    }
}

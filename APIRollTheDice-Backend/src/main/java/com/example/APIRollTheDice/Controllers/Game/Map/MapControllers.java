package com.example.APIRollTheDice.Controllers.Game.Map;

import com.example.APIRollTheDice.Enum.CreatedItemType;
import com.example.APIRollTheDice.Model.DTO.GameDTO.MapDTO.LayoutDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.MapDTO.MapDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.TokenDTO.TokenPlacedDTO;
import com.example.APIRollTheDice.Model.DTO.ReponseDTO.LayoutResponseDTO;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Map.Layout;
import com.example.APIRollTheDice.Model.Obj.Game.Map.Map;
import com.example.APIRollTheDice.Service.Game.Map.MapService;
import com.example.APIRollTheDice.Service.Game.Token.TokenService;
import com.example.APIRollTheDice.Service.User.UserCreationContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/map")
public class MapControllers {
    private final UserCreationContentService userCreationContentService;
    private final MapService mapService;
    private final TokenService tokenService;

    public MapControllers(UserCreationContentService userCreationContentService, MapService mapService, TokenService tokenService) {
        this.tokenService = tokenService;
        this.userCreationContentService = userCreationContentService;
        this.mapService = mapService;
    }

    // ------------------- Map -------------------------

    @PostMapping("/CreateMap/{userID}")
    public ResponseEntity<MapDTO> CreateMap(@PathVariable Long userId, @RequestBody MapDTO mapDTO){
        Map map = mapService.CreatMap(mapService.mapDTOToEntity(mapDTO));

        UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
        userCreationContentDTO.setUserId(userId);
        userCreationContentDTO.setCreatedItemId(map.getId());
        userCreationContentDTO.setCreatedItemType(CreatedItemType.MAP);
        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

        return ResponseEntity.ok(mapService.mapEntityToDTO(map));
    }

    @PutMapping("/UpdateMap")
    public ResponseEntity<MapDTO> UpdateMap(@RequestBody MapDTO mapDTO){
        Map map = mapService.UpdateMap(mapService.mapDTOToEntity(mapDTO));
        return ResponseEntity.ok(mapService.mapEntityToDTO(map));
    }

    @DeleteMapping("/DeleteMap/{mapID}")
    public ResponseEntity<String> DeleteMap(@PathVariable Long mapID) {
        mapService.DeleteMap(mapID);
        userCreationContentService.DeleteByCreatedItemId(mapID,CreatedItemType.MAP);
        return ResponseEntity.ok("Map deleted successfully.");
    }

    @GetMapping("/GetMapAllMapByGameBundleId/{GameBundleId}")
    public ResponseEntity<List<MapDTO>> GetMapAllMapByGameBundleId(@PathVariable Long GameBundleId){
        List<Map> maps = mapService.GetAllMapByGameBundle(GameBundleId);
        List<MapDTO> mapDTOS = maps.stream().map(mapService::mapEntityToDTO).toList();
        return ResponseEntity.ok(mapDTOS);
    }

    // ------------------- Layout -------------------------

    @PostMapping("/CreateLayout")
    public ResponseEntity<LayoutDTO> CreateLayout(@RequestBody LayoutDTO layoutDTO){
        Layout layout = mapService.CreateLayout(mapService.layoutDTOToEntity(layoutDTO));
        return ResponseEntity.ok(mapService.layoutEntityToDTO(layout));
    }

    @PutMapping("/UpdateLayout")
    public ResponseEntity<LayoutDTO> UpdateLayout(@RequestBody LayoutDTO layoutDTO) {
        Layout layout = mapService.UpdateLayout(mapService.layoutDTOToEntity(layoutDTO));
        return ResponseEntity.ok(mapService.layoutEntityToDTO(layout));
    }

    @DeleteMapping("/DeleteLayout/{layoutID}")
    public ResponseEntity<String> DeleteLayout(@PathVariable Long layoutID) {
        mapService.DeleteLayout(layoutID);
        return ResponseEntity.ok("Layout deleted successfully.");
    }

    @GetMapping("/GetAllLayoutsByMapId/{mapID}")
    public ResponseEntity<List<LayoutResponseDTO>> GetAllLayoutsByMapId(@PathVariable Long mapID) {
        List<Layout> layouts = mapService.GetAllLayoutByMap(mapID);

        List<LayoutResponseDTO> layoutResponseDTOS = new ArrayList<>();

        for (Layout layout : layouts){

            List<TokenPlacedDTO> tokenPlacedDTOS = layout.getTokens()
                    .stream()
                    .map(tokenService::TokenPlacedToDTO)
                    .toList();


            LayoutResponseDTO layoutResponseDTO = new LayoutResponseDTO(
                    mapService.layoutEntityToDTO(layout),
                    tokenPlacedDTOS
            );

            layoutResponseDTOS.add(layoutResponseDTO);
        }

        return ResponseEntity.ok(layoutResponseDTOS);
    }

}

package com.example.APIRollTheDice.Service.Game.Map;

import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.GameInterface.GameBundleInterface;
import com.example.APIRollTheDice.Interface.GameInterface.MapInterface.LayoutInterface;
import com.example.APIRollTheDice.Interface.GameInterface.MapInterface.MapInterface;
import com.example.APIRollTheDice.Interface.GameInterface.TokenInterface.TokenPlacedInterface;
import com.example.APIRollTheDice.Mapper.Game.Map.LayoutMapper;
import com.example.APIRollTheDice.Mapper.Game.Map.MapMapper;
import com.example.APIRollTheDice.Model.DTO.GameDTO.MapDTO.LayoutDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.MapDTO.MapDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Map.Layout;
import com.example.APIRollTheDice.Model.Obj.Game.Map.Map;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {
    private final MapMapper mapMapper;
    private final LayoutMapper layoutMapper;
    private final  MapInterface mapInterface;
    private final LayoutInterface layoutInterface;
    private final GameBundleInterface gameBundleInterface;
    private final TokenPlacedInterface tokenPlacedInterface;


    public MapService(MapMapper mapMapper, MapInterface mapInterface, LayoutMapper layoutMapper, LayoutInterface layoutInterface,
                      GameBundleInterface gameBundleInterface, TokenPlacedInterface tokenPlacedInterface
    ) {
        this.mapMapper = mapMapper;
        this.mapInterface = mapInterface;
        this.layoutMapper = layoutMapper;
        this.layoutInterface = layoutInterface;
        this.gameBundleInterface = gameBundleInterface;
        this.tokenPlacedInterface = tokenPlacedInterface;
    }

    public Layout CreateLayout(Layout layout){
        return layoutInterface.save(layout);
    }

    public Layout UpdateLayout(Layout layout){
        Layout existing = layoutInterface.findById(layout.getId()).orElseThrow(()->  new NotFoundException("Layout not found"));

        existing.setName(layout.getName());
        existing.setTokens(layout.getTokens());
        existing.setIndexZ(layout.getIndexZ());
        existing.setBackgroundImageURL(layout.getBackgroundImageURL());

        return layoutInterface.save(layout);
    }

    public void DeleteLayout(Long id){
        if(layoutInterface.existsById(id)){
            layoutInterface.deleteById(id);
        }else throw new NotFoundException("Layout not found");

    }

    public List<Layout> GetAllLayoutByMap(Long idMap){
        return layoutInterface.findAllByMap_Id(idMap);
    }


    public LayoutDTO layoutEntityToDTO(Layout layout){
        return layoutMapper.toDTO(layout);
    }

    public Layout layoutDTOToEntity(LayoutDTO layoutDTO){
        Layout layout = layoutMapper.toEntity(layoutDTO);

        layout.setMap(mapInterface.findById(layoutDTO.getIdMap()).orElseThrow(()-> new NotFoundException("Map not found")));
        layout.setTokens(tokenPlacedInterface.findAllById(layoutDTO.getIdTokenPlaced()));


        return  layout;
    }

    public Map CreatMap(Map map){
        return mapInterface.save(map);
    }

    public Map UpdateMap(Map map){
        Map existing = mapInterface.findById(map.getId()).orElseThrow(()-> new NotFoundException("Map not found"));

        existing.setLayouts(map.getLayouts());
        existing.setCellSize(map.getCellSize());
        existing.setDescription(map.getDescription());
        existing.setGridColor(map.getGridColor());
        existing.setName(map.getName());
        existing.setCellSize(map.getCellSize());
        existing.setGridEnabled(map.isGridEnabled());
        existing.setGridThickness(map.getGridThickness());
        existing.setHeight(map.getHeight());
        existing.setWidth(map.getWidth());
        existing.setGridType(map.getGridType());

        existing.setGameBundle(map.getGameBundle());

        return mapInterface.save(existing);

    }

    public void DeleteMap(Long id){
        if(mapInterface.existsById(id))mapInterface.deleteById(id);
        else throw new NotFoundException("Map not found");
    }

    public List<Map> GetAllMapByGameBundle(Long idBundle){
       return mapInterface.findAllByGameBundle_Id(idBundle);
    }


    public MapDTO mapEntityToDTO(Map map){

        return   mapMapper.toDTO(map);
    }

    public Map mapDTOToEntity(MapDTO mapDTO){
        Map map = mapMapper.toEntity(mapDTO);
        map.setLayouts(layoutInterface.findAllById(mapDTO.getIdLayouts()));
        map.setGameBundle(gameBundleInterface.findAllById(mapDTO.getIdGameBundle()));

        return  map;
    }



}

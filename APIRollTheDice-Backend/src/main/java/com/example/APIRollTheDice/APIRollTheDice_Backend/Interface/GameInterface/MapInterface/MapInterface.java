package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.MapInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Map.Map;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MapInterface extends JpaRepository<Map,Long> {
    Optional<Map> findById(Long id);

    List<Map> findAllByGameBundle_Id(Long id);
}

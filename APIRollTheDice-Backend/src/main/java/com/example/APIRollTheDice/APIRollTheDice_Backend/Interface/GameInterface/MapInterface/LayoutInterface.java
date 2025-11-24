package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.MapInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Map.Layout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LayoutInterface extends JpaRepository<Layout,Long> {
    Optional<Layout> findById(Long id);

    List<Layout> findAllByMap_Id(Long id);
}

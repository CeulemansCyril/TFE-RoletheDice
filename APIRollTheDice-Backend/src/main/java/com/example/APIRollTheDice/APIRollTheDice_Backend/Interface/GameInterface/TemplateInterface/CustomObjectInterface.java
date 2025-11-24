package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.TemplateInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.CustomObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomObjectInterface extends JpaRepository<CustomObject,Long> {
    Optional<CustomObject> findById(Long id);

    List<CustomObject> findAllByGameBundle_id(Long id);
}

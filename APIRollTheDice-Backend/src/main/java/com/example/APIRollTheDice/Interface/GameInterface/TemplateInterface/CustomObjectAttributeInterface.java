package com.example.APIRollTheDice.Interface.GameInterface.TemplateInterface;

import com.example.APIRollTheDice.Model.Obj.Game.Template.CustomObjectAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomObjectAttributeInterface extends JpaRepository<CustomObjectAttribute,Long> {
    List<CustomObjectAttribute> findByCustomObjectId(Long customObjectId);
}

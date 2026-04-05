package com.example.APIRollTheDice.Interface.GameInterface.TemplateInterface;

import com.example.APIRollTheDice.Model.Obj.Game.Template.CustomObjectAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomObjectAttributeInterface extends JpaRepository<CustomObjectAttribute,Long> {
    List<CustomObjectAttribute> findByCustomObjectId(Long customObjectId);

    @Modifying
    @Transactional
    @Query("delete from CustomObjectAttribute a where a.templateField.id in :templateFieldIds")
    void deleteAllByTemplateField_IdIn(List<Long> templateFieldIds);
}

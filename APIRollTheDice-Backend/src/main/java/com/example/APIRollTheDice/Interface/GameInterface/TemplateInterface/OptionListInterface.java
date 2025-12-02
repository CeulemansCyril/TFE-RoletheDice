package com.example.APIRollTheDice.Interface.GameInterface.TemplateInterface;

import com.example.APIRollTheDice.Model.Obj.Game.Template.OptionList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionListInterface extends JpaRepository<OptionList,Long> {
    Optional<OptionList> findById(Long id);
    Optional<OptionList> findByTemplateField_Id(Long id);
}

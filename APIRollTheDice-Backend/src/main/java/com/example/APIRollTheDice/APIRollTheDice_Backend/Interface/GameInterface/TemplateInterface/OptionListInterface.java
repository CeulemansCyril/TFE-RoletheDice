package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.TemplateInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.OptionList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionListInterface extends JpaRepository<OptionList,Long> {
    Optional<OptionList> findById(Long id);
}

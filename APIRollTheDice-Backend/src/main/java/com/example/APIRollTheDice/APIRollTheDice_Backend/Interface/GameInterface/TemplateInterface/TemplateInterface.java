package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.TemplateInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateInterface extends JpaRepository<Template,Long> {
    Optional<Template> findById(Long id);
}

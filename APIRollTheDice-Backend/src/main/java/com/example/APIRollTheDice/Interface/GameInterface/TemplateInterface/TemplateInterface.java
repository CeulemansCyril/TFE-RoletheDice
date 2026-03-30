package com.example.APIRollTheDice.Interface.GameInterface.TemplateInterface;

import com.example.APIRollTheDice.Model.Obj.Game.Template.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TemplateInterface extends JpaRepository<Template, Long> {
    Optional<Template> findById(Long id);
    List<Template> findAllByGameBundle_Id(Long gameBundleID);
}

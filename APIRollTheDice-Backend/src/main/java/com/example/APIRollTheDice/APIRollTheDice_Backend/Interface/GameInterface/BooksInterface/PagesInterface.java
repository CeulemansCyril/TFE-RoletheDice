package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.BooksInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books.Pages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PagesInterface extends JpaRepository<Pages,Long> {
    Optional<Pages> findById(Long id);
    List<Pages> findAllByBook_Id(Long id);
}

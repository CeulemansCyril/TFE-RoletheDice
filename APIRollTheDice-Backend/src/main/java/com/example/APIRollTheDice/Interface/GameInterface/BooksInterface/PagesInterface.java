package com.example.APIRollTheDice.Interface.GameInterface.BooksInterface;

import com.example.APIRollTheDice.Model.Obj.Game.Books.Pages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PagesInterface extends JpaRepository<Pages,Long> {
    Optional<Pages> findById(Long id);
    List<Pages> findAllByBooks_Id(Long id);
}

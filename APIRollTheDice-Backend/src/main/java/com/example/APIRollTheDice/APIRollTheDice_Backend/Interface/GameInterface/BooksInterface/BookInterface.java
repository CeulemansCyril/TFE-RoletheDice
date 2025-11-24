package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.BooksInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookInterface extends JpaRepository<Book,Long> {
    Optional<Book> findById(Long id);

    List<Book> findAllByGame_Id(Long id);
    List<Book> findAllByGameBundle_Id(Long id);
}

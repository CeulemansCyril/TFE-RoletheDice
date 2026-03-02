package com.example.APIRollTheDice.Interface.GameInterface.BooksInterface;

import com.example.APIRollTheDice.Model.Obj.Game.Books.Chapter;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Pages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChapterInterface extends JpaRepository<Chapter,Long> {
    Optional<Chapter> findById(Long id);
     List<Chapter> findAllByBooks_Id(Long id);

}

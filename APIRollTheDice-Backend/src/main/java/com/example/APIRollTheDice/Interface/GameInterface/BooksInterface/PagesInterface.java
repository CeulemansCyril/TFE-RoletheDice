package com.example.APIRollTheDice.Interface.GameInterface.BooksInterface;

import com.example.APIRollTheDice.Model.Obj.Game.Books.Pages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PagesInterface extends JpaRepository<Pages,Long> {
    Optional<Pages> findById(Long id);
    List<Pages> findAllByChapter_Id(Long id);

    @Query(""" 
        SELECT p
        FROM Pages p
        JOIN FETCH p.chapter c
        JOIN FETCH c.books b
        WHERE b.id = :id
        ORDER BY p.pageNumber
        """)
    List<Pages> findByIdWithChapterAndBook(@Param("id")Long id);
}

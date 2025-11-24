package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Book;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.BookDTO.BookDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books.Pages;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Game;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "idPages", expression = "java(book.getPages() != null ? book.getPages().stream().map(Pages::getId).toList() : null)")
    @Mapping(source = "gameBundle.id", target = "idGameBundle")
    @Mapping(source = "game.id", target = "idGame")
    BookDTO toDTO(Book book);

    @Mapping(target = "pages", ignore = true)
    @Mapping(target = "gameBundle", ignore = true)
    @Mapping(target = "game", ignore = true)
    Book toEntity(BookDTO bookDTO);
}

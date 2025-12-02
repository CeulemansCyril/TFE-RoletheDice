package com.example.APIRollTheDice.Mapper.Game.Book;

import com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO.BookDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Pages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "pages", target = "idPages", qualifiedByName = "mapPagesToId")
    @Mapping(source = "gameBundle.id", target = "idGameBundle")
    @Mapping(source = "game.id", target = "idGame")
    BookDTO toDTO(Book book);

    @Mapping(target = "pages", ignore = true)
    @Mapping(target = "gameBundle", ignore = true)
    @Mapping(target = "game", ignore = true)
    Book toEntity(BookDTO bookDTO);

    @Named("mapPagesToId")
    default List<Long> mapPagesToId(List<Pages> pages) {
        return pages==null ? null : pages.stream().map(Pages::getId).toList();
    }

}

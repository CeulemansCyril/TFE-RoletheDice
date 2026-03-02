package com.example.APIRollTheDice.Mapper.Game.Book;

import com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO.BookDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Chapter;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Pages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "chapters", target = "idChapters", qualifiedByName = "mapChaptersToId")
    @Mapping(source = "gameBundle.id", target = "idGameBundle")
    @Mapping(source = "game.id", target = "idGame")
    BookDTO toDTO(Book book);

    @Mapping(target = "chapters", ignore = true)
    @Mapping(target = "gameBundle", ignore = true)
    @Mapping(target = "game", ignore = true)
    Book toEntity(BookDTO bookDTO);

    @Named("mapChaptersToId")
    default List<Long> mapChaptersToId(List<Chapter> chapters) {
        return chapters==null ? null : chapters.stream().map(Chapter::getId).toList();
    }

}

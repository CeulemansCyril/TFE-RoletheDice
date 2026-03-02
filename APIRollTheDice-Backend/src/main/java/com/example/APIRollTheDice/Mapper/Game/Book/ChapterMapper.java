package com.example.APIRollTheDice.Mapper.Game.Book;

import com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO.ChapterDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Chapter;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Pages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChapterMapper {
    @Mapping(source = "books.id", target = "idBook")
    @Mapping(source = "pages", target = "idPages", qualifiedByName = "mapPagesToId")
    ChapterDTO toDTO(Chapter chapter);

    @Mapping(target = "books", ignore = true)
    @Mapping(target = "pages", ignore = true)
    Chapter toEntity(ChapterDTO chapterDTO);

    @Named("mapPagesToId")
    default List<Long> mapPagesToId(List<Pages> pages) {
        return pages==null ? null : pages.stream().map(Pages::getId).toList();
    }
}

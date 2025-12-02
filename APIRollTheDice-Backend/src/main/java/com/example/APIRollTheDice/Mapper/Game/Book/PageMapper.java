package com.example.APIRollTheDice.Mapper.Game.Book;

import com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO.PagesDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Pages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PageMapper {

    @Mapping(source = "books.id", target = "idBook")
    PagesDTO toDTO(Pages pages);

    @Mapping(target = "books", ignore = true)
    Pages toEntity(PagesDTO pagesDTO);
}

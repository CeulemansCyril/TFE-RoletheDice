package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Book;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.BookDTO.PagesDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books.Pages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PageMapper {

    @Mapping(source = "book.id", target = "idBook")
    PagesDTO toDTO(Pages pages);

    @Mapping(target = "book", ignore = true)
    Pages toEntity(PagesDTO pagesDTO);
}

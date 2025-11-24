package com.example.APIRollTheDice.APIRollTheDice_Backend.Service;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.BooksInterface.BookInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.BooksInterface.PagesInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.GameBundleInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.GameInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Book.BookMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Book.PageMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.BookDTO.BookDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.BookDTO.PagesDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books.Pages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private PageMapper pageMapper;
    private PagesInterface pagesInterface;
    private BookMapper bookMapper;
    private BookInterface bookInterface;
    private GameInterface gameInterface;
    private GameBundleInterface gameBundleInterface;

    public BookService(PageMapper pageMapper, PagesInterface pagesInterface, BookMapper bookMapper, BookInterface bookInterface, GameInterface gameInterface, GameBundleInterface gameBundleInterface) {
        this.pageMapper = pageMapper;
        this.pagesInterface = pagesInterface;
        this.bookMapper = bookMapper;
        this.bookInterface = bookInterface;
        this.gameInterface = gameInterface;
        this.gameBundleInterface = gameBundleInterface;
    }

    public Pages CreatePages(Pages pages){
        return pagesInterface.save(pages);
    }

    public Pages UpdatePages(Pages pages){
        Pages existing = pagesInterface.findById(pages.getId())
                .orElseThrow(() -> new NotFoundException("Pages not found"));

        existing.setContent(pages.getContent());
        existing.setPageNumber(pages.getPageNumber());

        return pagesInterface.save(existing);
    }

    public void DeletePages(Long idPage){
        if(pagesInterface.existsById(idPage)){
            pagesInterface.deleteById(idPage);
        }else {
            throw  new NotFoundException("Pages not found");
        }
    }

    public List<Pages> GetAllPagesByBookId(Long id){
        return pagesInterface.findAllByBook_Id(id);
    }

    public PagesDTO PagesInterfaceToDTO(Pages pages){
        PagesDTO dto = pageMapper.toDTO(pages);
        return dto;
    }

    public Pages PagesDTOToEntity(PagesDTO pagesDTO){
        Pages pages = pageMapper.toEntity(pagesDTO);
        pages.setBook(bookInterface.findById(pagesDTO.getIdBook()).get());
        return pages;
    }


    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Book CreateBook(Book book){
        return bookInterface.save(book);
    }

    public Book UpdateBook(Book book){
        Book existing = bookInterface.findById(book.getId())
                .orElseThrow(() -> new NotFoundException("Book not found"));

        existing.setTitle(book.getTitle());
        existing.setType(book.getType());
        existing.setPages(book.getPages());


        return bookInterface.save(existing);
    }

    public void DeleteBook(Long idBook){
        if(bookInterface.existsById(idBook)){
            bookInterface.deleteById(idBook);
        }else {
            throw  new NotFoundException("Book not found");
        }
    }

    public List<Book> GetAllPagesByGameId(Long id){
        return bookInterface.findAllByGame_Id(id);
    }

    public List<Book> GetAllPagesByGameBundleId(Long id){
        return bookInterface.findAllByGameBundle_Id(id);
    }

    public Book GetBookById(Long id){
        return bookInterface.findById(id).get();
    }


    public BookDTO BookInterfaceToDTO(Book book){
        BookDTO bookDTO = bookMapper.toDTO(book);
        return bookDTO;
    }


    public Book BookDTOToEntity(BookDTO bookDTO){
        Book book = bookMapper.toEntity(bookDTO);
        if (bookDTO.getIdGame() != null) {
            book.setGame(gameInterface.findById(bookDTO.getIdGame()).get());
        } else {
            book.setGame(null);
        }
        if (bookDTO.getIdGameBundle() != null) {
            book.setGameBundle(gameBundleInterface.findById(bookDTO.getIdGameBundle()).get());
        } else {
            book.setGameBundle(null);
        }

        if (bookDTO.getIdPages() != null) {
            book.setPages(pagesInterface.findAllByBook_Id(bookDTO.getId()));
        } else {
            book.setPages(null);
        }

        return book;
    }















}

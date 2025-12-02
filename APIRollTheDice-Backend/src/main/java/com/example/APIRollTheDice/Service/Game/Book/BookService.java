package com.example.APIRollTheDice.Service.Game.Book;

import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.GameInterface.BooksInterface.BookInterface;
import com.example.APIRollTheDice.Interface.GameInterface.BooksInterface.PagesInterface;
import com.example.APIRollTheDice.Interface.GameInterface.GameBundleInterface;
import com.example.APIRollTheDice.Interface.GameInterface.GameInterface;
import com.example.APIRollTheDice.Mapper.Game.Book.BookMapper;
import com.example.APIRollTheDice.Mapper.Game.Book.PageMapper;
import com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO.BookDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO.PagesDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Pages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final PageMapper pageMapper;
    private final PagesInterface pagesInterface;
    private final BookMapper bookMapper;
    private final BookInterface bookInterface;
    private final GameInterface gameInterface;
    private final GameBundleInterface gameBundleInterface;

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

    public List<Pages> CreateManyPages(List<Pages> pagesList){
        return pagesInterface.saveAll(pagesList);
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
        return pagesInterface.findAllByBooks_Id(id);
    }

    public PagesDTO PagesInterfaceToDTO(Pages pages){
        return pageMapper.toDTO(pages);
    }

    public Pages PagesDTOToEntity(PagesDTO pagesDTO){
        Pages pages = pageMapper.toEntity(pagesDTO);

        if(pagesDTO.getIdBook() != null){
            pages.setBooks(bookInterface.findById(pagesDTO.getIdBook()).orElseThrow(()-> new NotFoundException("Book not found")));
        }

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
        return bookInterface.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
    }


    public BookDTO BookInterfaceToDTO(Book book){
        return bookMapper.toDTO(book);
    }


    public Book BookDTOToEntity(BookDTO bookDTO){
        Book book = bookMapper.toEntity(bookDTO);
        if (bookDTO.getIdGame() != null) {
            book.setGame(gameInterface.findById(bookDTO.getIdGame()).orElseThrow(() -> new NotFoundException("Game not found")));
        }
        if (bookDTO.getIdGameBundle() != null) {
            book.setGameBundle(gameBundleInterface.findById(bookDTO.getIdGameBundle()).orElseThrow(() -> new NotFoundException("Game Bundle not found")));
        }

        if (bookDTO.getIdPages() != null) {
            book.setPages(pagesInterface.findAllByBooks_Id(bookDTO.getId()));
        }

        return book;
    }















}

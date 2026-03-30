package com.example.APIRollTheDice.Service.Game.Book;

import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.GameInterface.BooksInterface.BookInterface;
import com.example.APIRollTheDice.Interface.GameInterface.BooksInterface.ChapterInterface;
import com.example.APIRollTheDice.Interface.GameInterface.BooksInterface.PagesInterface;
import com.example.APIRollTheDice.Interface.GameInterface.GameBundleInterface;
import com.example.APIRollTheDice.Interface.GameInterface.GameInterface;
import com.example.APIRollTheDice.Mapper.Game.Book.BookMapper;
import com.example.APIRollTheDice.Mapper.Game.Book.ChapterMapper;
import com.example.APIRollTheDice.Mapper.Game.Book.PageMapper;
import com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO.BookDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO.ChapterDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO.PagesDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Chapter;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Pages;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final PageMapper pageMapper;
    private final PagesInterface pagesInterface;

    private final ChapterMapper chapterMapper;
    private final ChapterInterface chapterInterface;

    private final BookMapper bookMapper;
    private final BookInterface bookInterface;

    private final GameInterface gameInterface;
    private final GameBundleInterface gameBundleInterface;

    public BookService(PageMapper pageMapper, PagesInterface pagesInterface, BookMapper bookMapper, BookInterface bookInterface, GameInterface gameInterface, GameBundleInterface gameBundleInterface
    ,ChapterMapper chapterMapper, ChapterInterface chapterInterface
    ) {
        this.pageMapper = pageMapper;
        this.pagesInterface = pagesInterface;
        this.bookMapper = bookMapper;
        this.bookInterface = bookInterface;
        this.gameInterface = gameInterface;
        this.gameBundleInterface = gameBundleInterface;
        this.chapterMapper = chapterMapper;
        this.chapterInterface = chapterInterface;
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

    public  List<Pages> UpdateManyPages(List<Pages> pagesList){
        List<Pages> updatePages = new ArrayList<>();
        for(Pages pages : pagesList){
            Pages existing = pagesInterface.findById(pages.getId())
                    .orElseThrow(() -> new NotFoundException("Pages not found"));

            existing.setContent(pages.getContent());
            existing.setPageNumber(pages.getPageNumber());
            updatePages.add(existing);
        }

        return pagesInterface.saveAll(updatePages);
    }

    public void DeletePages(Long idPage){
        if(pagesInterface.existsById(idPage)){
            pagesInterface.deleteById(idPage);
        }else {
            throw  new NotFoundException("Pages not found");
        }
    }

    public List<Pages> GetAllPagesByChapterId(Long id){
        return pagesInterface.findAllByChapter_Id(id);
    }
    public List<Pages> GetAllPagesByBookId(Long id){
        return pagesInterface.findByIdWithChapterAndBook(id);
    }

    public PagesDTO PagesInterfaceToDTO(Pages pages){
        return pageMapper.toDTO(pages);
    }

    public Pages PagesDTOToEntity(PagesDTO pagesDTO){
        Pages pages = pageMapper.toEntity(pagesDTO);

        if(pagesDTO.getIdChapter() != null){
            pages.setChapter(
                    chapterInterface.findById(pagesDTO.getIdChapter())
                            .orElseThrow(() -> new NotFoundException("Chapter not found PagesDTOToEntity for id " + pagesDTO.getIdChapter()))
            );        }

        return pages;
    }
    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Chapter CreateChapter(Chapter chapter){
        return chapterInterface.save(chapter);
    }

    public List<Chapter> CreateManyChapter(List<Chapter> chapterList){
        return chapterInterface.saveAll(chapterList);
    }

    public Chapter UpdateChapter(Chapter chapter) {
        Chapter existing = chapterInterface.findById(chapter.getId())
                .orElseThrow(() -> new NotFoundException("Chapter not found"));

        existing.setTitle(chapter.getTitle());
        existing.setChapterNumber(chapter.getChapterNumber());


        if (chapter.getPages() != null) {
            List<Long> keepPageIds = chapter.getPages().stream()
                    .map(Pages::getId)
                    .filter(id -> id != null && id > 0)
                    .toList();
            existing.getPages().removeIf(p -> !keepPageIds.contains(p.getId()));


            for (Pages page : chapter.getPages()) {
                page.setChapter(existing);
                if (!existing.getPages().contains(page)) {
                    existing.getPages().add(page);
                }
            }
        } else {
            existing.getPages().clear();
        }

        return chapterInterface.save(existing);
    }

    public List<Chapter> UpdateManyChapter(List<Chapter> chapters) {
        List<Chapter> chapterListUpdate = new ArrayList<>();

        for (Chapter chapter : chapters) {
            Chapter existing = chapterInterface.findById(chapter.getId())
                    .orElseThrow(() -> new NotFoundException("Chapter not found"));

            existing.setTitle(chapter.getTitle());
            existing.setChapterNumber(chapter.getChapterNumber());


            if (chapter.getPages() != null) {

                List<Long> keepPageIds = chapter.getPages().stream()
                        .map(Pages::getId)
                        .filter(id -> id != null && id > 0)
                        .toList();


                existing.getPages().removeIf(p -> !keepPageIds.contains(p.getId()));


                for (Pages page : chapter.getPages()) {
                    page.setChapter(existing);
                    if (!existing.getPages().contains(page)) {
                        existing.getPages().add(page);
                    }
                }
            } else {
                existing.getPages().clear();
            }

            chapterListUpdate.add(existing);
        }

        return chapterInterface.saveAll(chapterListUpdate);
    }
    public void DeleteChapter(Long idChapter) {
        if (chapterInterface.existsById(idChapter)) {
            chapterInterface.deleteById(idChapter);
        } else {
            throw new NotFoundException("Chapter not found");
        }
    }

    public List<Chapter> GetAllChaptersByBookId(Long id) {
        return chapterInterface.findAllByBooks_Id(id);
    }

    public ChapterDTO ChapterInterfaceToDTO(Chapter chapter) {
        return chapterMapper.toDTO(chapter);
    }

    public Chapter ChapterDTOToEntity(ChapterDTO chapterDTO) {
        Chapter chapter = chapterMapper.toEntity(chapterDTO);


        if (chapterDTO.getIdBook() != null) {
            Optional<Book> bookOpt = bookInterface.findById(chapterDTO.getIdBook());
            if (bookOpt.isEmpty()) {
                throw new NotFoundException("Book not found for id chapter " + chapterDTO.getIdBook());
            }
            chapter.setBooks(bookOpt.get());
        } else {

            chapter.setBooks(null);
        }


        if (chapterDTO.getId() != null && chapterDTO.getIdPages() != null) {
            chapter.setPages(pagesInterface.findAllByChapter_Id(chapterDTO.getId()));
        } else {
            chapter.setPages(new ArrayList<>());
        }

        return chapter;
    }

    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Book CreateBook(Book book){
        return bookInterface.save(book);
    }

    public Book UpdateBook(Book book){
        if (book.getTitle() == null || book.getTitle().isBlank()) {
            throw new IllegalArgumentException("Book title cannot be null or empty");
        }

        Book existing = bookInterface.findById(book.getId())
                .orElseThrow(() -> new NotFoundException("Book not found"));

        existing.setTitle(book.getTitle());
        existing.setType(book.getType());

        if (book.getChapters() != null) {

            List<Long> keepChapterIds = book.getChapters().stream()
                    .map(Chapter::getId)
                    .filter(id -> id != null && id > 0)
                    .toList();

            existing.getChapters().removeIf(chapter ->
                    chapter.getId() != null && !keepChapterIds.contains(chapter.getId())
            );
        }


        return bookInterface.save(existing);
    }

    @Transactional
    public void DeleteBook(Long idBook){
        if(bookInterface.existsById(idBook)){

            bookInterface.deleteById(idBook);
        }else {
            throw  new NotFoundException("Book not found");
        }
    }

    public List<Book> GetAllBooksByBundleId(Long id){
         return  bookInterface.findAllByGameBundle_Id(id);
    }

    public Book GetBookById(Long id){
        return bookInterface.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
    }


    public BookDTO BookInterfaceToDTO(Book book){
        return bookMapper.toDTO(book);
    }


    public Book BookDTOToEntity(BookDTO bookDTO){
        Book book = bookMapper.toEntity(bookDTO);
        if (bookDTO.getIdGame() != null &&  bookDTO.getIdGame()>0) {
            book.setGame(gameInterface.findById(bookDTO.getIdGame()).orElseThrow(() -> new NotFoundException("Game not found")));
        } else book.setGame(null);
        if (bookDTO.getIdGameBundle() != null &&  bookDTO.getIdGameBundle()>0) {
            book.setGameBundle(gameBundleInterface.findById(bookDTO.getIdGameBundle()).orElseThrow(() -> new NotFoundException("Game Bundle not found")));
        }else book.setGameBundle(null);

        if (bookDTO.getIdChapters() != null &&  !bookDTO.getIdChapters().isEmpty()) {
            book.setChapters(chapterInterface.findAllByBooks_Id(bookDTO.getId()));
        }else book.setChapters(null);

        return book;
    }















}

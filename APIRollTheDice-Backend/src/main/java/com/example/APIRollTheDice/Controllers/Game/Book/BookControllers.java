package com.example.APIRollTheDice.Controllers.Game.Book;

import com.example.APIRollTheDice.Enum.CreatedItemType;
import com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO.BookDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO.ChapterDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO.PagesDTO;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Chapter;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Pages;
import com.example.APIRollTheDice.Service.Game.Book.BookService;
import com.example.APIRollTheDice.Service.User.UserCreationContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class BookControllers {
    private final BookService bookService;
    private final UserCreationContentService userCreationContentService;

    public BookControllers(BookService bookService,UserCreationContentService userCreationContentService) {
        this.bookService = bookService;
        this.userCreationContentService=userCreationContentService;
    }

    // ------------------- Book ----------------------------

    @PostMapping("/createBook/{userID}")
    public ResponseEntity<BookDTO>CreateBook(@PathVariable Long userID,@RequestBody BookDTO bookDTO) {
        Book book = bookService.BookDTOToEntity(bookDTO);
        book.setId(null);
          book = bookService.CreateBook(book);

        UserCreationContentDTO userCreationContent = new UserCreationContentDTO();
        userCreationContent.setUserId(userID);
        userCreationContent.setCreatedItemType(CreatedItemType.BOOK);
        userCreationContent.setCreatedItemId(book.getId());

        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContent));

        return ResponseEntity.ok(bookService.BookInterfaceToDTO(book));
    }

    @PutMapping("/updateBook")
    public ResponseEntity<BookDTO>UpdateBook(@RequestBody BookDTO bookDTO) {

        Book book =bookService.BookDTOToEntity(bookDTO);

         book = bookService.UpdateBook(book);
        return ResponseEntity.ok(bookService.BookInterfaceToDTO(book));
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String>DeleteBook(@PathVariable Long id) {
        bookService.DeleteBook(id);
        userCreationContentService.DeleteByCreatedItemId(id,CreatedItemType.BOOK);
        return ResponseEntity.ok("Book deleted successfully.");
    }


    @GetMapping("/getBook/{id}")
    public ResponseEntity<BookDTO> GetBookByIdWithOutPages (@PathVariable Long id) {
        Book book = bookService.GetBookById(id);
        return ResponseEntity.ok(bookService.BookInterfaceToDTO(book));
    }

    @GetMapping("/getAllBooksByBundleId/{bundleId}")
    public ResponseEntity<List<BookDTO>> GetAllBooksWithOutPages (@PathVariable Long bundleId) {
        List<Book> bookList = bookService.GetAllBooksByBundleId(bundleId);
        List<BookDTO> bookDTOList = bookList.stream()
                .map(bookService::BookInterfaceToDTO)
                .toList();
        return ResponseEntity.ok(bookDTOList);
    }
    // ------------------- Chapters ----------------------------
    @PostMapping("/createChapter")
    public ResponseEntity<ChapterDTO>CreateChapter(@RequestBody ChapterDTO chapterDTO) {
        Chapter chapter =bookService.ChapterDTOToEntity(chapterDTO);
        chapter.setId(null);
        chapter = bookService.CreateChapter(chapter);
        return ResponseEntity.ok(bookService.ChapterInterfaceToDTO(chapter));
    }

    @PostMapping("/createManyChapter")
    public ResponseEntity<List<ChapterDTO>>CreateManyChapter(@RequestBody List<ChapterDTO> chapterDTOList){
        List<Chapter> chapterList = chapterDTOList.stream().map(bookService::ChapterDTOToEntity).toList();
        chapterList.forEach(c -> c.setId(null));
        chapterList = bookService.CreateManyChapter(chapterList);
        List<ChapterDTO> chapterDTOS = chapterList.stream().map(bookService::ChapterInterfaceToDTO).toList();
        return ResponseEntity.ok(chapterDTOS);
    }

    @PutMapping("/updateChapter")
    public ResponseEntity<ChapterDTO>UpdateChapter(@RequestBody ChapterDTO chapterDTO) {
        Chapter chapter = bookService.ChapterDTOToEntity(chapterDTO);
        System.out.println("chapter id : "+chapter.getId());
        chapter = bookService.UpdateChapter(chapter);
        return ResponseEntity.ok(bookService.ChapterInterfaceToDTO(chapter));
    }

    @PutMapping("/updateManyChapter")
    public ResponseEntity<List<ChapterDTO>> UpdateManyChapter(@RequestBody List<ChapterDTO> chapterDTOList){
        List<Chapter> chapterList = chapterDTOList.stream().map(bookService::ChapterDTOToEntity).toList();
        chapterList = bookService.UpdateManyChapter(chapterList);
        List<ChapterDTO> chapterDTOS = chapterList.stream().map(bookService::ChapterInterfaceToDTO).toList();
        return ResponseEntity.ok(chapterDTOS);
    }

    @DeleteMapping("/deleteChapter/{id}")
    public ResponseEntity<String>DeleteChapter(@PathVariable Long id) {
        bookService.DeleteChapter(id);
        return ResponseEntity.ok("Chapter deleted successfully.");
    }

    @GetMapping("/getChapterByBookId/{id}")
    public ResponseEntity<List<ChapterDTO>>GetChapterByBookId(@PathVariable Long id) {
        List<Chapter> chapterList = bookService.GetAllChaptersByBookId(id);
        List<ChapterDTO> chapterDTOList = chapterList.stream()
                .map(bookService::ChapterInterfaceToDTO)
                .toList();
        return ResponseEntity.ok(chapterDTOList);
    }

    // ------------------- Pages ----------------------------

    @PostMapping("/createPages")
    public ResponseEntity<PagesDTO>CreatePages(@RequestBody PagesDTO pagesDTO) {
        Pages pages = bookService.PagesDTOToEntity(pagesDTO);
        pages.setId(null);
         pages = bookService.CreatePages(pages);
        return ResponseEntity.ok(bookService.PagesInterfaceToDTO(pages));
    }

    @PostMapping("/createManyPages")
    public ResponseEntity<List<PagesDTO>>CreateManyPages(@RequestBody List<PagesDTO> pagesDTOList) {
        List<Pages> pagesList = pagesDTOList.stream()
                .map(bookService::PagesDTOToEntity)
                .toList();

        pagesList.forEach(p -> p.setId(null));

        List<Pages> createdPagesList = bookService.CreateManyPages(pagesList);
        List<PagesDTO> createdPagesDTOList = createdPagesList.stream()
                .map(bookService::PagesInterfaceToDTO)
                .toList();
        return ResponseEntity.ok(createdPagesDTOList);
    }

    @PutMapping("/updatePages")
    public ResponseEntity<PagesDTO>UpdatePages(@RequestBody PagesDTO pagesDTO) {
        Pages pages = bookService.UpdatePages(bookService.PagesDTOToEntity(pagesDTO));
        return ResponseEntity.ok(bookService.PagesInterfaceToDTO(pages));
    }
    @PutMapping("/updateManyPages")
    public ResponseEntity<List<PagesDTO>> UpdateManyPages(@RequestBody List<PagesDTO> pagesDTOList){
        List<Pages> pagesList = pagesDTOList.stream().map(bookService::PagesDTOToEntity).toList();
        pagesList =bookService.UpdateManyPages(pagesList);
        List<PagesDTO> createdPagesDTOList = pagesList.stream()
                .map(bookService::PagesInterfaceToDTO)
                .toList();
        return ResponseEntity.ok(createdPagesDTOList);
    }

    @DeleteMapping("/deletePages/{id}")
    public ResponseEntity<String>DeletePages(@PathVariable Long id) {
        bookService.DeletePages(id);
        return ResponseEntity.ok("Pages deleted successfully.");
    }

    @GetMapping("/getPagesByChapterId/{id}")
    public ResponseEntity<List<PagesDTO>>GetPagesByChapterId(@PathVariable Long id) {
        List<Pages> pagesList = bookService.GetAllPagesByChapterId(id);
        List<PagesDTO> pagesDTOList = pagesList.stream()
                .map(bookService::PagesInterfaceToDTO)
                .toList();
        return ResponseEntity.ok(pagesDTOList);
    }

    @GetMapping("/getPagesByBookId/{id}")
    public ResponseEntity<List<PagesDTO>>GetPagesByBookId(@PathVariable Long id) {
        List<Pages> pagesList = bookService.GetAllPagesByBookId(id);
        List<PagesDTO> pagesDTOList = pagesList.stream()
                .map(bookService::PagesInterfaceToDTO)
                .toList();
        return ResponseEntity.ok(pagesDTOList);
    }



}

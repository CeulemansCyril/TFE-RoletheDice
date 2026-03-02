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

    @PostMapping("/createBook/{userId}")
    public ResponseEntity<BookDTO>CreateBook(@PathVariable Long userID,@RequestBody BookDTO bookDTO) {
        Book book = bookService.CreateBook(bookService.BookDTOToEntity(bookDTO));

        UserCreationContentDTO userCreationContent = new UserCreationContentDTO();
        userCreationContent.setUserId(userID);
        userCreationContent.setCreatedItemType(CreatedItemType.BOOK);
        userCreationContent.setCreatedItemId(book.getId());

        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContent));

        return ResponseEntity.ok(bookService.BookInterfaceToDTO(book));
    }

    @PutMapping("/updateBook")
    public ResponseEntity<BookDTO>UpdateBook(@RequestBody BookDTO bookDTO) {
        Book book = bookService.UpdateBook(bookService.BookDTOToEntity(bookDTO));
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
    // ------------------- Chapters ----------------------------
    @PostMapping("/createChapter")
    public ResponseEntity<ChapterDTO>CreateChapter(@RequestBody ChapterDTO chapterDTO) {
        Chapter chapter = bookService.CreateChapter(bookService.ChapterDTOToEntity(chapterDTO));
        return ResponseEntity.ok(bookService.ChapterInterfaceToDTO(chapter));
    }

    @PutMapping("/updateChapter")
    public ResponseEntity<ChapterDTO>UpdateChapter(@RequestBody ChapterDTO chapterDTO) {
        Chapter chapter = bookService.UpdateChapter(bookService.ChapterDTOToEntity(chapterDTO));
        return ResponseEntity.ok(bookService.ChapterInterfaceToDTO(chapter));
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
        Pages pages = bookService.CreatePages(bookService.PagesDTOToEntity(pagesDTO));
        return ResponseEntity.ok(bookService.PagesInterfaceToDTO(pages));
    }

    @PostMapping("/createManyPages")
    public ResponseEntity<List<PagesDTO>>CreateManyPages(@RequestBody List<PagesDTO> pagesDTOList) {
        List<Pages> pagesList = pagesDTOList.stream()
                .map(bookService::PagesDTOToEntity)
                .toList();

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

    @DeleteMapping("/deletePages/{id}")
    public ResponseEntity<String>DeletePages(@PathVariable Long id) {
        bookService.DeletePages(id);
        return ResponseEntity.ok("Pages deleted successfully.");
    }

    @GetMapping("/getPagesByChapterId/{id}")
    public ResponseEntity<List<PagesDTO>>GetPagesByBookId(@PathVariable Long id) {
        List<Pages> pagesList = bookService.GetAllPagesByChapterId(id);
        List<PagesDTO> pagesDTOList = pagesList.stream()
                .map(bookService::PagesInterfaceToDTO)
                .toList();
        return ResponseEntity.ok(pagesDTOList);
    }



}

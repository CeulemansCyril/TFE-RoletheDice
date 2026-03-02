using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.GameDTO.BookDTO;
using Assets._Project.API.Model.Object.Game.Book;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace Assets._Project.API.Service.Game.Book
{
    public class BookService : ApiService
    {
        private CatchError onError;
        public BookService() : base("book")
        {
        }
        // ------------------- Book ----------------------------

        public Awaitable<BookDTO> CreateBook<BookDTO>(BookDTO book, long idUser)
        {
            return CreateAsync("/createBook/" + idUser, book);
        }
        public Awaitable<BookDTO> UpdateBook<BookDTO>(BookDTO book)
        {
            return UpdateAsync("/updateBook ", book);
        }

        public Awaitable<BookDTO> GetBookById<BookDTO>(long id)
        {
            return GetAsync<BookDTO>("/getBook/" + id);
        }

        public Awaitable<string> DeleteBook (long id)
        {
            return DeleteAsync("/deleteBook/" + id);
        }


        public Books BookDTOToBook(BookDTO bookDTO)
        {
            Books book = new Books();
            book.Id = bookDTO.Id;
            book.Title = bookDTO.Title;
            book.Types = bookDTO.Type;
            book.Chapters = new List<Chapter>();

            return book;
        }

        public BookDTO BookToBookDTO(BookDTO bookDTO, Books book)
        {
            bookDTO.Id = book.Id;
            bookDTO.Title = book.Title;
            bookDTO.Type = book.Types;
            bookDTO.IdChapter = new List<long>();
            foreach (Chapter chapter in book.Chapters)
            {
                bookDTO.IdChapter.Add(chapter.Id);
            }


            return bookDTO;
        }


        // ------------------- Pages ----------------------------

        public Awaitable<PageDTO> CreatePages<PageDTO>(PageDTO pages)
        {
            return CreateAsync("/createPages" , pages);
        }
         public Awaitable<PageDTO[]> CreateManyPages<PageDTO>(PageDTO[] pages){
            return CreateManyAsync("/createManyPages",pages);
        }
        
        public Awaitable<PageDTO> UpdatePages<PageDTO>(PageDTO book)
        {
            return UpdateAsync("/updateBook ", book);
        }
        
        public Awaitable<PageDTO[]> GetAllPagesByBookId<PageDTO>(long id)
        {
            return GetAllAsync<PageDTO>("/getPagesByBookId/" + id);
        }

        public Awaitable<string> DeletePage(long id)
        {
            return DeleteAsync("/deletePages/" + id);
        }

   

        public Page PageDTOToPage(PageDTO pageDTO)
        {
            Page page = new Page();
            page.Id = pageDTO.Id;
            page.PageNumber = pageDTO.PageNumber;
            page.Content = pageDTO.Content;
            return page;
        }

        public PageDTO PageToPageDTO(PageDTO pageDTO, Page page)
        {
            pageDTO.Id = page.Id;
            pageDTO.PageNumber = page.PageNumber;
            pageDTO.Content = page.Content;
            return pageDTO;
        }

        // ------------------- Chapter ----------------------------
        
        public Awaitable<ChapterDTO> CreateChapter<ChapterDTO>(ChapterDTO chapter)
        {
            return CreateAsync("/createChapter", chapter);
        }
        public Awaitable<ChapterDTO> UpdateChapter<ChapterDTO> (ChapterDTO chapter) { 
            return UpdateAsync("/updateChapter ", chapter);
        }

        public Awaitable<ChapterDTO[]> GetChapterByBookId<ChapterDTO>(long id)
        {
            return GetAllAsync<ChapterDTO>("/getChapterByBookId/" + id);
        }

        public Awaitable<string> DeleteChapter(ChapterDTO chapter)
        {
            return DeleteAsync("/deleteChapter/" + chapter.Id);
        }

        

       

     }
}
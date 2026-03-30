using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.GameDTO.BookDTO;
using Assets._Project.API.Model.Object.Game.Book;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
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

        public Awaitable<BookDTO> CreateBook(BookDTO book, long idUser)
        {

            return CreateAsync("/createBook/" + idUser, book);
        }
        public Awaitable<BookDTO> UpdateBook(BookDTO book)
        {

            return UpdateAsync("/updateBook", book);
        }

        public Awaitable<BookDTO> GetBookById(long id)
        {
            return GetAsync<BookDTO>("/getBook/" + id);
        }

        public Awaitable<BookDTO[]> GetAllBooksByBundleId(long id)
        {
            return GetAllAsync<BookDTO>("/getAllBooksByBundleId/" + id);
        }

        public Awaitable<string> DeleteBook(long id)
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

        public BookDTO BookToBookDTO(Books book)
        {
            BookDTO bookDTO = new BookDTO();
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

        public Awaitable<PageDTO> CreatePages(PageDTO pages)
        {
            return CreateAsync("/createPages", pages);
        }
        public Awaitable<PageDTO[]> CreateManyPages(PageDTO[] pages)
        {
            return CreateManyAsync("/createManyPages", pages);
        }

        public Awaitable<PageDTO> UpdatePages(PageDTO book)
        {
            return UpdateAsync("/updatePages", book);
        }

        public Awaitable<PageDTO[]> UpdateManyPages(PageDTO[] books)
        {
            return UpadateManyAsync("/updateManyPages", books);
        }

        public Awaitable<PageDTO[]> GetPagesByChapterId(long id)
        {
            return GetAllAsync<PageDTO>("/getPagesByChapterId/" + id);
        }

        public Awaitable<PageDTO[]> GetPagesByBookId(long bookId)
        {
            return GetAllAsync<PageDTO>("/getPagesByBookId/" + bookId);
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
            page.Title = pageDTO.Title;

            return page;
        }

        public PageDTO PageToPageDTO(Page page)
        {
            PageDTO pageDTO = new PageDTO();
            pageDTO.Id = page.Id;
            pageDTO.PageNumber = page.PageNumber;
            pageDTO.Content = page.Content;
            pageDTO.Title = page.Title;
            return pageDTO;
        }

        // ------------------- Chapter ----------------------------

        public Awaitable<ChapterDTO> CreateChapter(ChapterDTO chapter)
        {
            return CreateAsync("/createChapter", chapter);
        }

        public Awaitable<ChapterDTO[]> CreateManyChapter(ChapterDTO[] chapterDTOs)
        {
            return CreateManyAsync("/createManyChapter", chapterDTOs);
        }
        public Awaitable<ChapterDTO> UpdateChapter(ChapterDTO chapter)
        {
            return UpdateAsync("/updateChapter", chapter);
        }

        public Awaitable<ChapterDTO[]> UpdateManyChapter(ChapterDTO[] chapterDTOs)
        {
            return UpadateManyAsync("/updateManyChapter", chapterDTOs);
        }

        public Awaitable<ChapterDTO[]> GetChapterByBookId(long id)
        {
            return GetAllAsync<ChapterDTO>("/getChapterByBookId/" + id);
        }

        public Awaitable<string> DeleteChapter(ChapterDTO chapter)
        {
            return DeleteAsync("/deleteChapter/" + chapter.Id);
        }


        public Chapter ChapterDTOToChapter(ChapterDTO chapterDTO)
        {
            Chapter chapter = new Chapter();
            chapter.Id = chapterDTO.Id;
            chapter.Title = chapterDTO.Title;
            chapter.Pages = new List<Page>();

            chapter.ChapterNumber = chapterDTO.ChapterNumber;



            return chapter;
        }

        public ChapterDTO ChapterToChapterDTO(Chapter chapter, long idBook)
        {
            ChapterDTO chapterDTO = new ChapterDTO();
            chapterDTO.Id = chapter.Id;
            chapterDTO.Title = chapter.Title;
            chapterDTO.IdPages = new List<long>();
            foreach (Page page in chapter.Pages)
            {
                chapterDTO.IdPages.Add(page.Id);
            }
            chapterDTO.ChapterNumber = chapter.ChapterNumber;
            chapterDTO.IdBook = idBook;

            return chapterDTO;
        }

        public async Task<Books> LoadFullBook(long bookId)
        {

            BookDTO bookDTO = await GetBookById(bookId);
            Books book = BookDTOToBook(bookDTO);


            ChapterDTO[] chapters = await GetChapterByBookId(bookId);
            PageDTO[] pages = await GetPagesByBookId(bookId);

            Dictionary<long, Chapter> chapterMap = new Dictionary<long, Chapter>();

            foreach (ChapterDTO chapterDTO in chapters)
            {
                Chapter chapter = ChapterDTOToChapter(chapterDTO);

                chapterMap[chapterDTO.Id] = chapter;
                book.Chapters.Add(chapter);
            }
            foreach (PageDTO pageDTO in pages)
            {
                Page page = PageDTOToPage(pageDTO);

                if (chapterMap.TryGetValue(pageDTO.IdChapter, out Chapter chapter))
                {
                    chapter.Pages.Add(page);
                }
            }

            return book;
        }

        public async Task<Books> UpdateFullBook(Books book)
        {
            Debug.Log("BOOK reçu: " + book.Title);

            //  Mettre à jour le livre lui-même
            BookDTO bookDTO = BookToBookDTO(book);
            bookDTO = await UpdateBook(bookDTO);
            Books updatedBook = BookDTOToBook(bookDTO);

            //  Préparer les tableaux de Chapters
        
            ChapterDTO[] chapterDTOs = book.Chapters.Select(ch => ChapterToChapterDTO(ch, book.Id)).ToArray();

            for (int i = 0; i < chapterDTOs.Length; i++)
            {
                Debug.Log($"chapterDTOs id: {chapterDTOs[i].Id}");
                ChapterDTO chapterDTO = chapterDTOs[i];

                // Création ou mise à jour des chapters
                if (book.Chapters[i].Id <= 0)
                    chapterDTO = await CreateChapter(chapterDTO);
                else
                    chapterDTO = await UpdateChapter(chapterDTO);

                Chapter updatedChapter = ChapterDTOToChapter(chapterDTO);

                // Préparer les tableaux de Pages pour ce chapitre
                PageDTO[] pageDTOs = book.Chapters[i].Pages.Select(p =>
                {
                    var dto = PageToPageDTO(p);
                    dto.IdChapter = updatedChapter.Id;  
                    return dto;
                }).ToArray();

                // Séparer création et mise à jour
                PageDTO[] pagesToCreate = pageDTOs.Where(p => p.Id <= 0).ToArray();
                PageDTO[] pagesToUpdate = pageDTOs.Where(p => p.Id > 0).ToArray();

                // Appel aux endpoints batch
                if (pagesToCreate.Length > 0)
                    pagesToCreate = await CreateManyPages(pagesToCreate);

                if (pagesToUpdate.Length > 0)
                    pagesToUpdate = await UpdateManyPages(pagesToUpdate);

                // Ajouter toutes les pages au chapitre mis à jour
                updatedChapter.Pages = pagesToCreate.Concat(pagesToUpdate).Select(PageDTOToPage).ToList();

                // Ajouter le chapitre mis à jour au livre
                updatedBook.Chapters.Add(updatedChapter);
            }

            return updatedBook;
        }
        }
    }
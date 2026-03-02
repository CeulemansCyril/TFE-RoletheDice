using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace Assets._Project.API.Model.DTO.GameDTO.BookDTO
{
	public class ChapterDTO 
	{
        public long Id { get; set; }
        public String Title { get; set; }
        public int ChapterNumber { get; set; }
        public List<long> IdPages { get; set; }
        public long IdBook { get; set; }

        public ChapterDTO() { }

        public ChapterDTO(long id, string title, int chapterNumber, List<long> idPages, long idBook)
        {
            Id = id;
            Title = title;
            ChapterNumber = chapterNumber;
            IdPages = idPages;
            IdBook = idBook;
        }

    }
}
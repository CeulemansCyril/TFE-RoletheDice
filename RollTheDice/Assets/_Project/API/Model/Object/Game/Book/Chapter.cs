using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace Assets._Project.API.Model.Object.Game.Book
{
	public class Chapter 
	{
        public long Id { get; set; }
        public String Title { get; set; }
        public int ChapterNumber { get; set; }
        public List<Page> Pages { get; set; } = new List<Page>();

        public Chapter() { }
        public Chapter(long id, string title, int chapterNumber, List<Page> pages)
        {
            Id = id;
            Title = title;
            ChapterNumber = chapterNumber;
            Pages = pages;
        }

    }
}
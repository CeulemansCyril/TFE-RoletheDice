using System.Collections.Generic;
using RollTheDice.API.Enums;
 
 namespace RollTheDice.API.Models.Game.Books
{
    public class Book
    {
        public int Id { get; set;}
        public string Title { get; set;}
        public BookTypes Types { get; set;}
        public List<Page> Pages { get; set;} = new List<Page>();

        public Book(){}
        public Book(int id, string title, BookTypes types, List<Page> pages)
        {
            Id = id;
            Title = title;
            Types = types;
            Pages = pages;
        }

    }
}
using System.Collections.Generic;
using Assets._Project.API.Enums;

namespace Assets._Project.API.Model.Object.Game.Book
{
    public class Books
    {
        public long Id { get; set;}
        public string Title { get; set;}
        public BookTypes Types { get; set;}
        public List<Chapter> Chapters { get; set;} = new List<Chapter>();

        public Books(){}
        public Books(long id, string title, BookTypes types, List<Chapter> chapters)
        {
            Id = id;
            Title = title;
            Types = types;
            Chapters = chapters;
        }

    }
}
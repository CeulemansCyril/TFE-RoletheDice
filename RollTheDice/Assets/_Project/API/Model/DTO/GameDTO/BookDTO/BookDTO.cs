using System.Collections.Generic;
using Assets._Project.API.Enums;

namespace Assets._Project.API.Model.DTO.GameDTO.BookDTO
{
    public class BookDTO
    {
        public long Id { get; set;}
        public string Title { get; set;}
        public BookTypes Type { get; set;}
        public List<long> IdChapter { get; set;} = new List<long>();
        public long IdGame { get; set;}
        public long IdGameBundle { get; set;}

        public BookDTO(){}
        public BookDTO(long id, string title,BookTypes bookTypes, List<long> idCapter, long idGame, long idGameBundle)
        {
            Id = id;
            Title = title;
            Type = bookTypes;
            IdChapter = idCapter;
            IdGame = idGame;
            IdGameBundle = idGameBundle;

        }

    }
}
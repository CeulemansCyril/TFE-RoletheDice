using System.Collections.Generic;
using RollTheDice.API.Enums;

namespace RollTheDice.API.DTO.GameDTO.BookDTO
{
    public class BookDTO
    {
        public long Id { get; set;}
        public string Title { get; set;}
        public BookTypes Type { get; set;}
        public List<long> IdPages { get; set;} = new List<long>();
        public long IdGame { get; set;}
        public long IdGameBundle { get; set;}

        public BookDTO(){}
        public BookDTO(long id, string title,BookTypes bookTypes, List<long> idPages, long idGame, long idGameBundle)
        {
            Id = id;
            Title = title;
            Type = bookTypes;
            IdPages = idPages;
            IdGame = idGame;
            IdGameBundle = idGameBundle;

        }

    }
}
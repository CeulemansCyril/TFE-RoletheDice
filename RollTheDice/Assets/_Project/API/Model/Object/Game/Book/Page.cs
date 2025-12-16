 namespace RollTheDice.API.Models.Game.Books
{
    public class Page
    {
        public int Id { get; set; }
        public string Content { get; set; } = "";

        public int PageNumber { get; set; }

        public Page(){}
        public Page(int id, string content, int pageNumber)
        {
            Id = id;
            Content = content;
            PageNumber = pageNumber;
        }
         

    }
}
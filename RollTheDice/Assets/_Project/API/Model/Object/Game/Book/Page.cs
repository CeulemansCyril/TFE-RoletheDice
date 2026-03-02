namespace Assets._Project.API.Model.Object.Game.Book
{
    public class Page
    {
        public long Id { get; set; }
        public string Content { get; set; } = "";

        public int PageNumber { get; set; }
        public string Title { get; set; }

        public Page(){}
        public Page(long id, string content, int pageNumber,string title)
        {
            Id = id;
            Content = content;
            PageNumber = pageNumber;
            Title = title;
        }
         

    }
}
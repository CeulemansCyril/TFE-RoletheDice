 namespace RollTheDice.API.DTO.GameDTO.BookDTO
{
    public class PageDTO{
     public long Id {get; set;}
    public string Content {get; set;}
    public int PageNumber {get; set;}
    public long IdBook {get; set;}

    public PageDTO(){}
    public PageDTO(long id, string content, int pageNumber, long idBook)
    {
        Id = id;
        Content = content;
        PageNumber = pageNumber;
        IdBook = idBook;
    }
}
}
namespace Assets._Project.API.Model.DTO.GameDTO.BookDTO
{
    public class PageDTO{
     public long Id {get; set;}
    public string Content {get; set;}
    public int PageNumber {get; set;}
    public long IdChapter {get; set;}
    
    public string Title {get; set;}
    public PageDTO(){}
    public PageDTO(long id, string content, int pageNumber, long idChapter, string title)
        {
            Id = id;
            Content = content;
            PageNumber = pageNumber;
            IdChapter = idChapter;
            Title = title;
        }
    }
}
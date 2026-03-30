using Assets._Project.API.Enums;
using Assets._Project.API.Model.DTO.GameDTO.BookDTO;
using Assets._Project.API.Model.Object.Game.Book;
using Assets._Project.API.Service.Game.Book;
using Assets._Project.Localization;
using Assets._Project.Scrip.Scene;
using Assets._Project.Scrip.ScripForScene.Bundle;
using Assets._Project.Scrip.ScripForScene.Login;
using NUnit.Framework.Internal.Execution;
using System.Collections.Generic;
using System.Threading.Tasks;
using TMPro;

using UnityEngine;
using UnityEngine.UI;

public class BookItemManager : MonoBehaviour
{
    [SerializeField] private Transform bookItemParent;
    [SerializeField] private GameObject bookItemPrefab;
    [SerializeField] private Button CreateTemplateButton;

    [SerializeField] private TMP_Text NoTemplateText;
    [SerializeField] private TMP_InputField searchBar;

    private List<Books> book = new List<Books>();
    private BookService bookService;

    private void Start()
    {
        CreateTemplateButton.onClick.AddListener(() => CreateTemplateClick());
        searchBar.onValueChanged.AddListener(OnSearchChanged);
        NoTemplateText.gameObject.SetActive(false);
        bookService = new BookService();
       
        LoadList();
    }
    private async void LoadList() {

      
            BookDTO[] dto = await bookService?.GetAllBooksByBundleId(BundleSession.Intance.Bundle.Id);
       
            if (dto != null) { 
                foreach ( BookDTO bookDTO in dto)
                {
                    Books books = bookService.BookDTOToBook(bookDTO);
                    book.Add(books);
                }
            }
      

        if (bookItemPrefab != null || book != null || book.Count >0 )
        {
            foreach (Books bok in book)
            {
                CreateTemplateItem(bok);
            }
        }
        else
        {
           NoTemplateText.gameObject.SetActive(true);
            
        }
    
    }

    private void CreateTemplateItem(Books book)
    {
        GameObject bookItemObject = Instantiate(bookItemPrefab, bookItemParent);
        BookItem bookItem = bookItemObject.GetComponent<BookItem>();
        if (bookItem != null)
        {
            bookItem.Book = book;
            bookItem.BookName.text = book.Title;
        }
        bookItem.DeleteButon.onClick.AddListener(() => DeleteClick(bookItem));
        bookItem.ModifButon.onClick.AddListener(() => ModifyClick(bookItem));
    }

    private void DeleteClick(BookItem bookItem)
    {
        PopUpManager.Instance.ShowConfirmPopUp(
            LocalizationControllers.Instance.GetLocalizedValue("PopUpDelBook.title"),
           LocalizationControllers.Instance.GetLocalizedValue("PopUpDelBook.Message"),
            async () =>
            {
                Books bookToRemove = bookItem.Book;

                if (book.Contains(bookToRemove))
                {
                    book.Remove(bookToRemove);

                    await bookService
                        .DeleteBook(bookToRemove.Id);
                        

                    Destroy(bookItem.gameObject);
                    Refresh();
                }
            },
            () => { }
        );
    }

   

    private async void ModifyClick(BookItem bookItem )
    {
        Books bookToModify = await bookService.LoadFullBook(bookItem.Book.Id);
        
        SceneData.SetData("BookToModify", bookToModify);
        SceneLoader.Instance.LoadScene(Scene.BookMaker);

    }

    private void CreateTemplateClick()
    {      
        string name = "";
        PopUpManager.Instance.ShowInputPopUp(LocalizationControllers.Instance.GetLocalizedValue("PopUpCreatTemplate.title"), LocalizationControllers.Instance.GetLocalizedValue("PopUpCreatTemplate.Message"), NameTemplateConfirm,null); 
    }

    private async void NameTemplateConfirm(string name)
    {

        if(!string.IsNullOrEmpty(name)) {

            if (this.book.Exists(t => t.Title.ToLower() == name.ToLower()))
            {
                //TODO : Ajouter un message d'erreur disant que le nom existe déjà 
                // PopUpManager.Instance.("Erreur", "Un template avec ce nom existe déjà.");
                return;
            }



            BookDTO bookDTO = new BookDTO();
            bookDTO.Title = name;
            bookDTO.IdGameBundle = BundleSession.Intance.Bundle.Id;
            bookDTO.Type = BookTypes.RULE; 


              bookDTO = await bookService.CreateBook(bookDTO, UserSession.Intance.UserID);

            Books book = bookService.BookDTOToBook(bookDTO);

            SceneData.SetData("BookToCreate", book);
            SceneLoader.Instance.LoadScene(Scene.BookMaker);
        }
    }

    public void Refresh()
    {
        bool hasItems = bookItemParent.childCount > 1;
        NoTemplateText.gameObject.SetActive(!hasItems);
    }

    private void OnSearchChanged(string searchText)
    {
        searchText = searchText.ToLower();
        ClearList(); 

        bool hasItems = false;
        foreach (Books template in book)
        {
            if (string.IsNullOrEmpty(searchText) || template.Title.ToLower().Contains(searchText))
            {
                CreateTemplateItem(template);
                hasItems = true;
            }
        }
        NoTemplateText.gameObject.SetActive(!hasItems);
    }

    private void ClearList()
    {
        for (int i = bookItemParent.childCount - 1; i >= 0; i--)
        {
            if(bookItemParent.GetChild(i).gameObject != NoTemplateText.gameObject) Destroy(bookItemParent.GetChild(i).gameObject);
        }
    }

   

}

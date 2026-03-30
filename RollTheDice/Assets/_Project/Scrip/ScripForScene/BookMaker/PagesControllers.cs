using Assets._Project.API.Model.Object.Game.Book;
using Assets._Project.API.Service.Game.Book;
using Assets._Project.Localization;
using Assets._Project.Scrip.Scene;
using Assets._Project.Scrip.ScripUI.ColorPicker;
using Assets._Project.Scrip.ScripUI.Tree;

using System.Collections.Generic;
using System.Linq;
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;
using Button = UnityEngine.UI.Button;
using Page = Assets._Project.API.Model.Object.Game.Book.Page;
using Toggle = UnityEngine.UI.Toggle;


public class PagesControllers : MonoBehaviour
{
    [SerializeField] private TMP_InputField field;
    [SerializeField] private TMP_Text textComponent;
    [SerializeField] private RectTransform rectTransform;

    [SerializeField] private int padding;

    [SerializeField] private TreeModel tree;

    [SerializeField] private Toggle boldToggle;
    [SerializeField] private Toggle italicToggle;
    [SerializeField] private Toggle underlinedToggle;

    [SerializeField] private Toggle alignLeftToggle;
    [SerializeField] private Toggle alignRightToggle;
    [SerializeField] private Toggle alignCenterToggle;

    [SerializeField] private ColorPicker colorPicker;

    [SerializeField] private Button undo;
    [SerializeField] private Button redo;

    [SerializeField] private Button addPage;
    [SerializeField] private Button addChapter;

    [SerializeField] private Button closeButton;
    [SerializeField] private Button SaveButton;

    [SerializeField] private TMP_Text title;
    [SerializeField] private Button rename;
    [SerializeField] private TMP_InputField renameInput;
    private string oldName = "";

    [SerializeField] private TMP_Dropdown bookTypes;

    private bool IsSave = false;

    private Chapter currentChapter;
    private Page currentPage;

    private TreeNodeModel currentChapterNode;
    private TreeNodeModel currentPageNode;

    private static long currentId = -1;

    private Stack<string> undoStack = new Stack<string>();
    private Stack<string> redoStack = new Stack<string>();

    private bool isUndoRedo = false;


    private bool isPaginating = false;

    private Books books = new Books();

    private BookService bookService;
    private bool isLoading = false;



    private void Start()
    {

        field.onValueChanged.AddListener(OnInputValueChanged);
        if (SceneData.HasData("BookToModify"))
        {
            Books bookToModify = SceneData.GetData<Books>("BookToModify");
            books = bookToModify;
            Debug.Log("BookToModify found in SceneData. Loading book: " + bookToModify.Title);
            Debug.Log("BookToModify Chapters count: " + bookToModify.Chapters?.Count);
            LoadBook(bookToModify);
            SceneData.RemoveData("BookToModify");
        }
        else
        {
            Books bookToCreate = SceneData.GetData<Books>("BookToCreate");
            books = bookToCreate;
            LoadNewBook(bookToCreate);
            SceneData.RemoveData("BookToCreate");
        }

        tree.OnNodeSelected += OnPageSelected;

        loadToggle();

        colorPicker.onColorChange += ApplyColor;

        undo.onClick.AddListener(Undo);
        redo.onClick.AddListener(Redo);

        addChapter.onClick.AddListener(AddChapter);
        addPage.onClick.AddListener(AddPages);

        tree.RemoveItem += RemoveElement;
        tree.DroppenItem += ItemDroppend;

        SaveButton.onClick.AddListener(OnSaveButtonClicked);
        closeButton.onClick.AddListener(OnCloseButton);

        rename.onClick.AddListener(StartRename);
        renameInput.onEndEdit.AddListener(EndRename);

        LoadComboBoxType();

        bookService = new BookService();
    }

    private void LoadComboBoxType()
    {
        bookTypes.options.Clear();
        foreach (var type in System.Enum.GetValues(typeof(Assets._Project.API.Enums.BookTypes)))
        {
            bookTypes.options.Add(new TMP_Dropdown.OptionData(type.ToString()));
        }
    }


    private void loadToggle()
    {
        boldToggle.onValueChanged.AddListener((value) =>
        {
            if (value)
            {
                Bold();
                boldToggle.SetIsOnWithoutNotify(true);
            }
        });

        italicToggle.onValueChanged.AddListener((value) => { if (value) { Italic(); italicToggle.SetIsOnWithoutNotify(true); } });

        underlinedToggle.onValueChanged.AddListener((value) => { if (value) { Underline(); underlinedToggle.SetIsOnWithoutNotify(true); } });

        alignLeftToggle.onValueChanged.AddListener((value) => { if (value) { Alignleft(); alignLeftToggle.SetIsOnWithoutNotify(true); } });
        alignCenterToggle.onValueChanged.AddListener((value) => { if (value) { Aligncenter(); alignCenterToggle.SetIsOnWithoutNotify(true); } });

        alignRightToggle.onValueChanged.AddListener((value) => { if (value) { Alignright(); alignRightToggle.SetIsOnWithoutNotify(true); } });

    }



    private void LoadNewBook(Books books)
    {

        if (books.Chapters == null || books.Chapters.Count == 0)
        {
            Chapter defaultChapter = new Chapter { Title = "Chapter 1", Id = currentId };
            currentId--;
            Page defaultPage = new Page { PageNumber = 1, Title = "Page 1", Content = "", Id = currentId };
            currentId--;
            defaultChapter.Pages = new List<Page> { defaultPage };
            books.Chapters = new List<Chapter> { defaultChapter };
        }


        List<TreeNodeModel> nodes = new List<TreeNodeModel>();

        foreach (Chapter chapter in books.Chapters)
        {
            TreeNodeModel chapterNode = new TreeNodeModel { Label = chapter.Title, Data = chapter, IsExpanded = true, Id = chapter.Id };
            foreach (Page page in chapter.Pages)
            {
                TreeNodeModel pageNode = new TreeNodeModel { Label = page.Title, Data = page, Id = page.Id };
                pageNode.Parent = chapterNode;
                chapterNode.AddChild(pageNode);
            }
            nodes.Add(chapterNode);
        }




        if (string.IsNullOrEmpty(books.Title)) title.text = LocalizationControllers.Instance.GetLocalizedValue("BookMaker.NewBook");
        else title.text = books.Title;

        tree.Init(nodes);
        tree.renamed += RenameElement;
        LoadFirstPage(nodes);
    }

    private void LoadBook(Books books)
    {


        if (books.Chapters == null || books.Chapters.Count == 0)
        {
            Chapter defaultChapter = new Chapter { Title = "Chapter 1", Id = currentId };
            currentId--;
            Page defaultPage = new Page { PageNumber = 1, Title = "Page 1", Content = "", Id = currentId };
            currentId--;

            defaultChapter.Pages = new List<Page> { defaultPage };
            books.Chapters = new List<Chapter> { defaultChapter };
        }

        this.books = books;
        bookTypes.value = (int)books.Types;

        List<TreeNodeModel> nodes = new List<TreeNodeModel>();

        foreach (Chapter chapter in books.Chapters)
        {
            TreeNodeModel chapterNode = new TreeNodeModel { Label = chapter.Title };
            chapterNode.Data = chapter;
            chapterNode.IsExpanded = true;

            foreach (Page page in chapter.Pages)
            {

                TreeNodeModel pageNode = new TreeNodeModel { Label = page.Title, Id = page.Id };
                pageNode.Data = page;
                pageNode.Parent = chapterNode;

                chapterNode.AddChild(pageNode);

            }
            nodes.Add(chapterNode);
        }
        isLoading = true;
        title.text = books.Title;
        tree.Init(nodes);
        tree.renamed += RenameElement;
        LoadFirstPage(nodes);
    }

    private void RenameElement(string newName, long id)
    {
        Chapter chapter = books.Chapters.FirstOrDefault(c => c.Id == id);
        if (chapter != null)
        {
            chapter.Title = newName;
            return;
        }

        Page page = books.Chapters
            .SelectMany(c => c.Pages)
            .FirstOrDefault(p => p.Id == id);

        if (page != null)
        {
            page.Title = newName;
        }
    }

    private void LoadFirstPage(List<TreeNodeModel> nodes)
    {
        if (nodes.Count > 0)
        {
            TreeNodeModel firstChapter = nodes[0];

            if (firstChapter.Children.Count > 0)
            {
                TreeNodeModel firstPage = firstChapter.Children[0];
                OnPageSelected(firstPage);
            }
        }
    }

    private void OnInputValueChanged(string value)
    {

        if (isPaginating || isUndoRedo) return;

        undoStack.Push(currentPage.Content);
        redoStack.Clear();



        currentPage.Content = value;
        OnOverflow();
        IsSave = false;
    }

    private void OnOverflow()
    {
        if (isPaginating) return;

        textComponent.ForceMeshUpdate();


        int lineCount = textComponent.textInfo.lineCount;
        float lineHeight = textComponent.textInfo.lineInfo[0].lineHeight;

        float totalHeight = lineCount * lineHeight + padding;

        bool isOverflowing = totalHeight > rectTransform.rect.height;



        if (totalHeight > rectTransform.rect.height)

        {
            isPaginating = true;
            Paginate();
            isPaginating = false;
        }


    }

    private void Paginate()
    {
        string fullText = textComponent.text;

        int cutIndex = FindCutIndex();

        if (cutIndex < 0) return;

        while (cutIndex >= 0 && char.IsWhiteSpace(fullText[cutIndex]))
        {
            cutIndex--;
        }

        string pageText = fullText.Substring(0, cutIndex).TrimEnd();
        string remainingText = fullText.Substring(cutIndex).TrimStart();

        isPaginating = true;

        field.SetTextWithoutNotify(pageText);
        currentPage.Content = pageText;

        CreateNewPage(remainingText);
        isPaginating = false;
    }

    private void CreateNewPage(string txt)
    {
        if (currentChapter == null || currentPage == null) return;

        int newPageNumber = currentChapter.Pages.Count + 1;

        Page newPage = new Page { PageNumber = newPageNumber, Content = txt };
        newPage.Id = currentId;
        currentId--;

        currentChapter.Pages.Add(newPage);
        currentPage = newPage;

        TreeNodeModel pageNode = new TreeNodeModel { Label = "Page : " + newPage.PageNumber, Data = newPage, Id = newPage.Id };

        currentChapterNode.AddChild(pageNode);

        currentPageNode = pageNode;

        tree.AddNode(currentPageNode, currentChapterNode);

        field.SetTextWithoutNotify(txt);
        IsSave = false;
    }

    public void OnPageSelected(TreeNodeModel node)
    {

        if (node == null) return;

        if (node.GetData() is Chapter chapter)
        {
            tree.DisableNode(currentChapterNode);

            currentChapter = chapter;
            currentChapterNode = node;
        }
        else if (node.GetData() is Page page)
        {
            tree.DisableNode(currentPageNode);

            currentPage = page;
            currentPageNode = node;

            currentChapter = (Chapter)node.Parent.GetData();
            currentChapterNode = node.Parent;

            field.text = page.Content;

            redoStack.Clear();
            undoStack.Clear();
        }
    }



    private int FindCutIndex()
    {
        textComponent.ForceMeshUpdate();

        TMP_TextInfo textInfo = textComponent.textInfo;

        int safeLength = Mathf.Min(textInfo.characterCount, textInfo.characterInfo.Length);

        float pageBottom = -rectTransform.rect.height;


        for (int i = safeLength - 1; i >= 0; i--)
        {
            if (textInfo.characterInfo[i].isVisible) continue;

            if (textInfo.characterInfo[i].bottomLeft.y >= pageBottom) return i;
            {
                return i;
            }
        }

        return -1;
    }

    private void WrapSelection(string openTag, string closeTag)
    {



        int start = Mathf.Min(field.selectionStringAnchorPosition, field.selectionStringFocusPosition);
        int end = Mathf.Max(field.selectionStringAnchorPosition, field.selectionStringFocusPosition);


        if (end - start <= 0) return;

        undoStack.Push(field.text);
        redoStack.Clear();

        string txt = field.text;

        string selectedText = txt.Substring(start, end - start);



        field.text = txt.Substring(0, start) + openTag + txt.Substring(start, end - start) + closeTag + txt.Substring(end);
        field.caretPosition = end + openTag.Length + closeTag.Length;


    }

    private void ApplyColor(string hexColor)
    {
        WrapSelection($"<color={hexColor}>", "</color>");
    }

    private void ApplySize(int size)
    {
        WrapSelection($"<size={size}>", "</size>");
    }

    private void Bold()
    {
        if (field.text.Contains("<b>")) EraseBalise("<b>", "</b>");
        else WrapSelection("<b>", "</b>");
    }

    private void Italic()
    {
        if (field.text.Contains("<i>")) EraseBalise("<i>", "</i>");
        else WrapSelection("<i>", "</i>");
    }

    private void Underline()
    {
        if (field.text.Contains("<u>")) EraseBalise("<u>", "</u>");
        else WrapSelection("<u>", "</u>");
    }

    private void Alignleft()
    {
        EraseAlign();
        WrapSelection("<align=left>", "</align>");
    }

    private void Aligncenter()
    {
        EraseAlign();
        WrapSelection("<align=center>", "</align>");
    }

    private void Alignright()
    {
        EraseAlign();
        WrapSelection("<align=right>", "</align>");
    }

    private void EraseAlign()
    {
        string txt = field.text;
        if (txt.Contains("</align>"))
        {
            if (txt.Contains("<align=center>")) txt = txt.Replace("<align=center>", "").Replace("</align>", "");
            else if (txt.Contains("<align=left>")) txt = txt.Replace("<align=left>", "").Replace("</align>", "");
            else if (txt.Contains("<align=right>")) txt = txt.Replace("<align=right>", "").Replace("</align>", "");
        }

        field.text = txt;
        IsSave = false;
    }

    private void EraseBalise(string openTag, string closeTag)
    {
        string txt = field.text;
        txt = txt.Replace(openTag, "").Replace(closeTag, "");
        field.text = txt;
        field.caretPosition = txt.Length;
        IsSave = false;
    }

    private void AddPages()
    {
        if (currentChapter == null || currentChapterNode == null)
            return;

        int newPageNumber = currentChapter.Pages.Count + 1;

        Page page = new Page { PageNumber = newPageNumber, Content = "", Id = currentId };
        currentId--;

        currentChapter.Pages.Add(page);

        TreeNodeModel pageNode = new TreeNodeModel { Label = "Page : " + page.PageNumber, Data = page, Id = page.Id };
        pageNode.Parent = currentChapterNode;

        if (currentPage != null) tree.DisableNode(currentPageNode);
        tree.AddNode(pageNode, currentChapterNode);
        IsSave = false;
    }

    private void AddChapter()
    {
        int newChapterNumber = tree.Nodes.Count + 1;

        Chapter chapter = new Chapter { Title = $"Chapter  {newChapterNumber}", Id = currentId };
        currentId--;

        TreeNodeModel chapterNode = new TreeNodeModel { Label = chapter.Title, Data = chapter, Id = chapter.Id };

        books.Chapters.Add(chapter);
        tree.AddNode(chapterNode);

        if (currentChapter != null) tree.DisableNode(currentPageNode);
        currentChapter = chapter;
        IsSave = false;
    }



    private void Undo()
    {
        if (undoStack.Count == 0) return;

        isUndoRedo = true;

        string previous = undoStack.Pop();

        redoStack.Push(field.text);

        field.SetTextWithoutNotify(previous);
        currentPage.Content = previous;

        isUndoRedo = false;
        IsSave = false;
    }

    private void Redo()
    {
        if (redoStack.Count == 0) return;

        isUndoRedo = true;
        string previous = redoStack.Pop();

        undoStack.Push(field.text);
        field.SetTextWithoutNotify(previous);
        currentPage.Content = previous;

        isUndoRedo = false;
        IsSave = false;
    }

    private void RemoveElement(TreeNodeModel node)
    {
        if (node == null) return;

        var data = node.Data;

        if (data == null) return;

        if (data is Page page)
        {
            Chapter chapter = (Chapter)node.Parent.GetData();
            chapter.Pages.Remove(page);
        }
        else if (data is Chapter chapter)
        {
            books.Chapters.Remove(chapter);
        }

        if (node == currentChapterNode) currentChapterNode = null;
        else if (node == currentPageNode) currentPageNode = null;
        IsSave = false;
    }

    private void ItemDroppend(TreeNodeModel draggedNode, TreeNodeModel targetNode)
    {
        if (draggedNode == null || targetNode == null)
            return;


        if (!(draggedNode.GetData() is Page draggedPage))
            return;

        if (!(targetNode.GetData() is Chapter targetChapter))
            return;

        Chapter oldChapter = (Chapter)draggedNode.Parent.GetData();

        if (oldChapter == targetChapter)
            return;


        oldChapter.Pages.Remove(draggedPage);
        targetChapter.Pages.Add(draggedPage);


        draggedNode.Parent.Children.Remove(draggedNode);
        draggedNode.Parent = targetNode;
        targetNode.AddChild(draggedNode);


        RecalculatePageNumbers(oldChapter);
        RecalculatePageNumbers(targetChapter);


    }
    private void RecalculatePageNumbers(Chapter chapter)
    {
        for (int i = 0; i < chapter.Pages.Count; i++)
        {
            chapter.Pages[i].PageNumber = i + 1;
        }
    }



    //TODO: implement save
    private async void SaveBook()
    {
        string newTitle = title.text?.Trim();
        if (string.IsNullOrEmpty(newTitle) || newTitle == LocalizationControllers.Instance.GetLocalizedValue("BookMaker.NewBook"))
        {
            newTitle = "Book " + Random.Range(1, 1000);
        }

        books.Title = newTitle;

        books.Types = (Assets._Project.API.Enums.BookTypes)bookTypes.value;

        await bookService.UpdateFullBook(books);

        IsSave = true;
    }

    private void OnClose()
    {
        SceneLoader.Instance.LoadScene(Scene.MainMenuCreate);
    }

    private void OnSaveButtonClicked()
    {
        SaveButton.interactable = false;
        SaveBook();
        isLoading = true;
        SaveButton.interactable = true;
    }

    private void OnCloseButton()
    {
        if (!IsSave)
        {
            bool userConfirmed = false;

            PopUpManager.Instance.ShowConfirmPopUp(LocalizationControllers.Instance.GetLocalizedValue("PopUpSave.title"), LocalizationControllers.Instance.GetLocalizedValue("PopUpSave.Message"),
                () => OnConfirmCloseWithSave(), () => OnConfirmCloseWithoutSave());

        }
        else
        {
            OnClose();
        }
    }

    private void OnConfirmCloseWithSave()
    {
        SaveBook();
        OnClose();
    }

    private void OnConfirmCloseWithoutSave()
    {
        OnClose();
    }


    public void StartRename()
    {
        oldName = title.text;

        title.gameObject.SetActive(false);

        renameInput.gameObject.SetActive(true);
        renameInput.text = oldName;
        renameInput.Select();
        renameInput.ActivateInputField();

    }

    private void EndRename(string newName)
    {
        if (string.IsNullOrWhiteSpace(newName))
            newName = oldName;



        title.text = newName;

        renameInput.gameObject.SetActive(false);
        title.gameObject.SetActive(true);


    }

    public void OnPointerClick(PointerEventData eventData)
    {
        StartRename();
    }
}

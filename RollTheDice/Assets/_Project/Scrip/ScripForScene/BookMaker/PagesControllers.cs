using Assets._Project.API.Model.Object.Game.Book ;
using Assets._Project.Scrip.ScripForScene.BookMaker;
using Assets._Project.Scrip.ScripUI.ColorPicker;
using Assets._Project.Scrip.ScripUI.Tree;
 
using System.Collections.Generic;
 
using TMPro;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.UIElements;
using Button = UnityEngine.UI.Button;
using Page = Assets._Project.API.Model.Object.Game.Book.Page;
using Toggle = UnityEngine.UI.Toggle;


public class PagesControllers : MonoBehaviour
{
    [SerializeField]private TMP_InputField field;
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

 

    private Chapter currentChapter;
    private Page currentPage;

    private TreeNodeModel currentChapterNode;
    private TreeNodeModel currentPageNode;

    

    private Stack<string> undoStack = new Stack<string>();
    private Stack<string> redoStack = new Stack<string>();

    private bool isUndoRedo = false;


    private bool isPaginating = false;

    private Books books = new Books();

    private void Start()
    {
       
        field.onValueChanged.AddListener(OnInputValueChanged);
            if (SceneData.HasData("BookToModify"))
            {
                Books bookToModify = SceneData.GetData<Books>("BookToModify");
                books = bookToModify;
                LoadBook(bookToModify);
                SceneData.RemoveData("BookToModify");
            }
            else
            {
                LoadNewBook();
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

        alignLeftToggle.onValueChanged.AddListener((value) => { if(value) { Alignleft(); alignLeftToggle.SetIsOnWithoutNotify(true); } });
        alignCenterToggle.onValueChanged.AddListener((value) => { if(value) { Aligncenter(); alignCenterToggle.SetIsOnWithoutNotify(true); } });

        alignRightToggle.onValueChanged.AddListener((value) => { if (value) { Alignright(); alignRightToggle.SetIsOnWithoutNotify(true); } });

    }
          


    private void LoadNewBook() 
    { 
        List<TreeNodeModel> nodes = new List<TreeNodeModel>();


        /*Chapter chapter = new Chapter { Title = "Chapter 1" };
        Page page = new Page { PageNumber = 1, Content = "" };

        chapter.Pages.Add(page);
        books.Chapters.Add(chapter);

        currentChapter = chapter;
        currentPage = page;

        TreeNodeModel chapterNode = new TreeNodeModel { Label = chapter.Title, Data = chapter };
        currentChapterNode = chapterNode;

        TreeNodeModel pageNode = new TreeNodeModel { Label = "Page : " + page.PageNumber, Data = page };
        currentPageNode = pageNode;
        currentPageNode.Parent = currentChapterNode;

        currentChapterNode.AddChild(currentPageNode);
        
        nodes.Add(currentChapterNode);*/

         

        // TODO : Traduction pour livre de base
        tree.Init("Book", nodes);
    }

    private void LoadBook(Books books)
    {
        this.books = books; 
        List<TreeNodeModel> nodes = new List<TreeNodeModel>();
        foreach (Chapter chapter in books.Chapters)
        {
            TreeNodeModel chapterNode = new TreeNodeModel { Label = chapter.Title };
            chapterNode.Data = chapter;
           
            foreach (Page page in chapter.Pages)
            {
                
                TreeNodeModel pageNode = new TreeNodeModel { Label = page.Title  };
                pageNode.Data = page;
                pageNode.Parent = chapterNode;
                chapterNode.AddChild(pageNode);
            }
            nodes.Add(chapterNode);
        }
        tree.Init(books.Title, nodes);
    }

    private void OnInputValueChanged(string value)
    {

        if (isPaginating || isUndoRedo) return;

        undoStack.Push(currentPage.Content);
        redoStack.Clear();



        currentPage.Content = value;
        OnOverflow();
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

        if (cutIndex <0)return;

        while (cutIndex >= 0 && char.IsWhiteSpace(fullText[cutIndex]))
        {
            cutIndex--;
        }

          string pageText = fullText.Substring(0, cutIndex  ).TrimEnd();
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

        currentChapter.Pages.Add(newPage);
        currentPage = newPage;

        TreeNodeModel pageNode = new TreeNodeModel { Label = "Page : " + newPage.PageNumber, Data = newPage };

        currentChapterNode.AddChild(pageNode);

        currentPageNode = pageNode;

        tree.AddNode(currentPageNode, currentChapterNode);

        field.SetTextWithoutNotify(txt);
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

        int safeLength = Mathf.Min(textInfo.characterCount , textInfo.characterInfo.Length);

        float pageBottom = -rectTransform.rect.height ;

        Debug.Log("characterInfo : " + textInfo.characterInfo[0].character);
        for (int i = safeLength-1; i >=0 ; i--)
        {
            if (textInfo.characterInfo[i].isVisible) continue;
            
            if(textInfo.characterInfo[i].bottomLeft.y >= pageBottom) return i;
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


        if (end - start <= 0) return ;

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

    private void EraseAlign( )
    {
       string txt =  field.text;
        if (txt.Contains("</align>"))
        {
            if(txt.Contains("<align=center>")) txt = txt.Replace("<align=center>", "").Replace("</align>", "");
            else if (txt.Contains("<align=left>")) txt = txt.Replace("<align=left>", "").Replace("</align>", "");
            else if (txt.Contains("<align=right>")) txt = txt.Replace("<align=right>", "").Replace("</align>", "");             
        }
        
        field.text = txt;
    }

    private void EraseBalise(string openTag, string closeTag)
    {
        string txt = field.text;
        txt = txt.Replace(openTag, "").Replace(closeTag, "");
        field.text = txt;
        field.caretPosition = txt.Length;

    }

    private void AddPages()
    {
        if (currentChapter == null || currentChapterNode == null)
            return;

        int newPageNumber = currentChapter.Pages.Count + 1;

        Page page = new Page { PageNumber = newPageNumber, Content = "" };

        currentChapter.Pages.Add(page);

        TreeNodeModel pageNode = new TreeNodeModel { Label = "Page : " + page.PageNumber, Data = page };
        pageNode.Parent = currentChapterNode;

        if (currentPage != null) tree.DisableNode(currentPageNode);
        tree.AddNode(pageNode,currentChapterNode);
    }

    private void AddChapter()
    {
        int newChapterNumber = tree.Nodes.Count + 1;

        Chapter chapter = new Chapter { Title = $"Chapter  {newChapterNumber}" };

        TreeNodeModel chapterNode = new TreeNodeModel { Label = chapter.Title, Data = chapter };

        books.Chapters.Add(chapter);
        tree.AddNode(chapterNode);

        if (currentChapter != null) tree.DisableNode(currentPageNode);
        currentChapter = chapter;
    }

    

    private void Undo()
    {
        if(undoStack.Count == 0) return;

        isUndoRedo = true;

        string previous = undoStack.Pop();

        redoStack.Push(field.text);

        field.SetTextWithoutNotify(previous);
        currentPage.Content = previous;

        isUndoRedo = false;
    }

    private void Redo()
    {
        if (redoStack.Count == 0 ) return;

        isUndoRedo = true;
        string previous = redoStack.Pop();

        undoStack.Push(field.text);
        field.SetTextWithoutNotify(previous);
        currentPage.Content = previous;

        isUndoRedo = false;
    }

    private void RemoveElement(TreeNodeModel node)
    {
        if (node == null) return;

        var data = node.Data;

        if(data == null) return;

        if (data is Page page)
        {
            Chapter chapter = (Chapter)node.Parent.GetData();
            chapter.Pages.Remove(page);
        }
        else if (data is Chapter chapter)
        {
            books.Chapters.Remove(chapter);
        }

        if(node == currentChapterNode) currentChapterNode = null;
        else if (node == currentPageNode) currentPageNode = null;
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
    private void SaveBook()
    {
         
        
    }
}

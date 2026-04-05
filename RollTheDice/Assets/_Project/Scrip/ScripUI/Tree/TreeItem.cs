using Assets._Project.Localization;
using Assets._Project.Scrip.ScripUI.Tree;

public class TreeItem : MonoBehaviour, IPointerEnterHandler, IPointerExitHandler, IBeginDragHandler, IEndDragHandler, IDragHandler, IDropHandler, IPointerClickHandler
{
    [SerializeField] private Button expandBut;
    [SerializeField] private TMP_Text expandText;
    [SerializeField] private GameObject indentSpacer;
    [SerializeField] private TMP_Text label;
    [SerializeField] private TMP_InputField rename;

    private bool isRenaming = false;
    private bool isDragging = false;

    [SerializeField] private Image background;

    [SerializeField] private Color normalColor = Color.white;
    [SerializeField] private Color highlightColor = Color.lightBlue;
    [SerializeField] private Color activeColor = Color.lightBlue;

    [SerializeField] private Button remove;

    public bool isExpandable = false;
    private bool isActive = false;
    private int indentSize = 20;

    private float lastClickTime = 0;


    private CanvasGroup canvasGroup;
    private RectTransform rectTransform;
    private Transform originalParent;


    public Action<TreeItem> removeItem;
    public Action<TreeItem> OnItemClicked;
    public Action<string, long> renamed;

    public long ID;


    public TreeNodeModel Data { get; private set; }


    public event Action<TreeItem, TreeItem> OnItemDropped;

    private void Start()
    {

        remove.onClick.AddListener(RemoveBut);
    }

    private void Awake()
    {
        canvasGroup = gameObject.AddComponent<CanvasGroup>();
        rectTransform = GetComponent<RectTransform>();
        rename.gameObject.SetActive(false);
        rename.onEndEdit.AddListener(OnRenameSubmit);
    }

    private void RemoveBut()
    {

        PopUpManager.Instance.ShowConfirmPopUp(LocalizationControllers.Instance.GetLocalizedValue("PopUpDel.title"), LocalizationControllers.Instance.GetLocalizedValue("PopUpDel.Message") + label.text, () => ValideRemove(), null);

    }

    private void ValideRemove()
    {
        removeItem.Invoke(this);
    }




    public void Bind(TreeNodeModel obj)
    {
        ID = obj.Id;
        Data = obj;

        int indent = 0 * indentSize;

        indentSpacer.GetComponent<LayoutElement>().preferredWidth = indent;

        Debug.Log("obj name : " + obj.Label);
        label.text = obj.Label;
        Debug.Log("label name : " + label.text);

        rectTransform.anchoredPosition = new Vector2(indent, rectTransform.anchoredPosition.y);

        UpdateExpandIcon();

    }

    public void OnPointerClick(PointerEventData eventData)
    {
        if (isRenaming) return;


        if (eventData.clickCount == 2)
        {

            lastClickTime = 0f;
            StarRename();
        }
        else
        {

            lastClickTime = Time.time;
            HandleSingleClick();
        }
    }


    private void HandleSingleClick()
    {
        isExpandable = !isExpandable;
        isActive = true;
        if (Data.Children.Count > 0) UpdateExpandIcon();
        OnItemClicked?.Invoke(this);
    }


    public void UpdateExpandIcon()
    {

        if (Data.Children.Count == 0)
        {
            expandText.text = "---";
        }
        else
        {
            expandText.text = isExpandable ? "▼" : "▶";
        }

        expandText.ForceMeshUpdate();

    }


    public void OnPointerEnter(PointerEventData eventData)
    {

        background.color = highlightColor;
        expandBut.GetComponent<Image>().color = highlightColor;
        expandText.color = Color.white;

    }

    public void OnPointerExit(PointerEventData eventData)
    {
        if (!isActive)
        {
            background.color = normalColor;
            expandBut.GetComponent<Image>().color = normalColor;
            expandText.color = Color.black;
        }
    }

    public void OnBeginDrag(PointerEventData eventData)
    {
        if (isRenaming) return;

        isDragging = true;
        originalParent = transform.parent;
        canvasGroup.blocksRaycasts = false;
    }

    public void OnEndDrag(PointerEventData eventData)
    {
        canvasGroup.blocksRaycasts = true;
        transform.SetParent(originalParent);
        StartCoroutine(ResetDrag());
    }
    private IEnumerator ResetDrag()
    {
        yield return null;
        isDragging = false;
    }

    public void OnDrag(PointerEventData eventData)
    {
        rectTransform.position = eventData.position;
    }

    public void OnDrop(PointerEventData eventData)
    {
        TreeItem dropItem = eventData.pointerDrag?.GetComponent<TreeItem>();
        if (dropItem != null && dropItem != this)
        {
            OnItemDropped?.Invoke(dropItem, this);
        }
    }

    private void StarRename()
    {
        if (isRenaming) return;
        isRenaming = true;

        label.gameObject.SetActive(false);
        rename.gameObject.SetActive(true);

        rename.text = label.text;
        rename.Select();
        rename.ActivateInputField();

    }

    private void OnRenameSubmit(string Newname)
    {
        if (!isRenaming) return;

        if (string.IsNullOrWhiteSpace(Newname))
        {
            CancelRename();
            return;
        }

        label.text = Newname;

        Data.Label = Newname;


        FinishRename();
    }

    private void CancelRename()
    {
        rename.text = label.text;
        FinishRename();
    }

    private void FinishRename()
    {
        isRenaming = false;
        rename.gameObject.SetActive(false);
        label.gameObject.SetActive(true);
        renamed.Invoke(label.text, Data.Id);
    }

    public void IsActive(bool isActive)
    {
        this.isActive = isActive;
        if (!isActive) background.color = normalColor;
    }
}

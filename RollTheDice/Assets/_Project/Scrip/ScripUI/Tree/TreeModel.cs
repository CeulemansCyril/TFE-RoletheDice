using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using System;
 

namespace Assets._Project.Scrip.ScripUI.Tree
{
	public class TreeModel: MonoBehaviour
	{
		[SerializeField] private GameObject treeItemPrefab;
		[SerializeField] private Transform treeContainer;
		[SerializeField] private TMP_Text title;
        /// TODO: bug visuelle
        public List<TreeNodeModel> Nodes { get; set; } = new List<TreeNodeModel>();	
		
        private TreeNodeModel SelectedNode = new TreeNodeModel();

        private int currentIndex = 1;
        public Action<TreeNodeModel> OnNodeSelected;
        public Action<TreeNodeModel> RemoveItem;
        public Action<TreeNodeModel,TreeNodeModel> DroppenItem;

        public void Init(string title,List<TreeNodeModel> node)
		{
			this.title.text = title;
			this.Nodes = node;

            ClearTree();

            foreach (var item in node)
            {
                AddNodeToView(item);
				 
            }
        }

        ///CRUD

        public void AddNode( TreeNodeModel newNode, TreeNodeModel parent = null)
        {
            if (newNode == null) return;

        
            if (parent == null)
            {
                if (!Nodes.Contains(newNode))
                {
                    Nodes.Add(newNode);
                }
            }
            else
            {
         
                if (parent.Children.Contains(newNode))
                    return;

                newNode.Parent = parent;
                parent.AddChild(newNode);
                
                TreeItem parentItem = GetTreeItemFromNode(parent);
                parentItem.UpdateExpandIcon();
            }

            RefreshTree();
        }


        public void RemoveNode(TreeNodeModel node)
        {
            RemoveItem.Invoke(node);
    
            if (node.Parent != null)
            {
                node.Parent.RemoveChild(node);
            }
            else
            {
                Nodes.Remove(node);
            }
            
            RefreshTree();
             
        }

        public void UpdateNode(TreeNodeModel node, string newName)
        {
            node.Label = newName;
            RefreshTree();
        }

        public TreeNodeModel FindNode(string label)
        {
            foreach (var root in Nodes)
            {
                var found = FindNodeRecursive(root, label);
                if (found != null)
                    return found;
            }
            return null;
        }

        public TreeNodeModel GetActiveNode()
        {
            return SelectedNode;
        }




        ///////////////////////////////////////////////////////////////////////////////////////

       
       

        private void AddNodeToView(TreeNodeModel node)
        {
            GameObject gameObject = Instantiate(treeItemPrefab, treeContainer);
            TreeItem treeItem = gameObject.GetComponent<TreeItem>();

            treeItem.Bind(node);
            treeItem.OnItemClicked += OnItemClicked;
            treeItem.OnItemDropped += OnItemDropped;
            treeItem.removeItem += RemoveTreeItem;
        }

        private void RemoveTreeItem(TreeItem treeItem)
        {
            RemoveNode(treeItem.Data);
        }

        private void RefreshTree()
        {
            ClearTree();
            currentIndex = 1;

            foreach (TreeNodeModel node in Nodes)
            {
              
                DrawNodeRecursive(node);
            }
        }


        private void ClearTree()
        {
            foreach (Transform child in treeContainer)
                Destroy(child.gameObject);

        }



        private void DrawNodeRecursive(TreeNodeModel node )
        {
            AddNodeToView(node);

            if (node.IsExpanded)
            {
                foreach (var child in node.Children)
                {
                    DrawNodeRecursive(child);
                }
            }
        }





        private TreeNodeModel FindNodeRecursive(TreeNodeModel current, string label)
        {
            if (current.Label == label)
                return current;
            foreach (var child in current.Children)
            {
                var found = FindNodeRecursive(child, label);
                if (found != null)
                    return found;
            }
            return null;
        }


        private void OnItemClicked(TreeItem item)
        {
            TreeNodeModel node = item.Data;

            OnNodeSelected?.Invoke(node);

            SelectedNode = node;

            if (node.Children.Count > 0)
                node.IsExpanded = !node.IsExpanded;

            //RefreshTree();
        }



        private bool IsChildOf(TreeNodeModel parent, TreeNodeModel child)
        {
            return parent.Children.Contains(child);
        }

       
 
 

        private TreeNodeModel FindParentRecursive(TreeNodeModel current, TreeNodeModel target)
        {
            foreach (var child in current.Children)
            {
                if (child == target)
                    return current;

                var parent = FindParentRecursive(child, target);
                if (parent != null)
                    return parent;
            }

            return null;
        }

        private void OnItemDropped(TreeItem draggedItem, TreeItem targetItem)
        {
            TreeNodeModel draggedNode = draggedItem.Data;
            TreeNodeModel targetNode = targetItem.Data;

            DroppenItem.Invoke(draggedNode, targetNode);

            if (draggedNode == targetNode)return;

            if (IsChildOf(draggedNode,targetNode))return;

            if(draggedNode.Parent != null) draggedNode.Parent.RemoveChild(draggedNode);
    
           

            targetNode.AddChild(draggedNode);
            draggedNode.Parent = targetNode;

            RefreshTree();
           
        }

        public string GetTitle()
        {
            return title.text;
        }

        public void DisableNode(TreeNodeModel node)
        {
            if (node == null) return;

            TreeItem treeItem = GetTreeItemFromNode(node);

            if (treeItem == null) return;

            treeItem.IsActive(false);

        }
        public TreeItem GetTreeItemFromNode(TreeNodeModel node)
        {
            TreeItem[] items = treeContainer.GetComponentsInChildren<TreeItem>(true);

            foreach (var item in items)
            {
                if (item.Data == node)
                    return item;
            }

            return null;
        }


    }
}
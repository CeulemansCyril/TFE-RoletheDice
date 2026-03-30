using System;
using System.Collections.Generic;
using System.Text;

namespace Assets._Project.Scrip.ScripUI.Tree
{
    public class TreeNodeModel
    {
    
        public long Id { get; set; }
        public string Label;
        public bool IsExpanded =false;
        public object Data;

        public TreeNodeModel Parent;
        public List<TreeNodeModel> Children = new();

        public int depth = 0;

        public TreeNodeModel( string label, object data,int depth)
        {
            
            Label = label;
            Data = data;
            this.depth = depth;
        }
        public TreeNodeModel() { }

        public void AddChild(TreeNodeModel child)
        {
            child.Parent = this;
            Children.Add(child);
            IsExpanded = true;
        }

        

        public void RemoveChild(TreeNodeModel node) {
            if (Children.Contains(node))
            {
                Children.Remove(node);
                node.Parent = null;
            }
        }

        public Object GetData()
        {
            return Data;
        }




    }
}

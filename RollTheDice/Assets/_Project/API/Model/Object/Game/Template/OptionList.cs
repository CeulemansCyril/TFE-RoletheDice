using System.Collections.Generic;

namespace RollTheDice.API.Models.Game.Template
{
    public class OptionList
    {
        public long Id { get; set;}
        public string Name { get; set;}
        public List<string> Options { get; set;} = new List<string>();

        public OptionList(){}
        public OptionList(long id, string name, List<string> options)
        {
            Id = id;
            Name = name;
            Options = options;
        }

    }
}
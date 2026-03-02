using System;
using System.Collections.Generic;
using System.Text;
using Unity.VisualScripting;

namespace Assets._Project.Localization
{
    public sealed class LanguageEnum
    {
        public static readonly LanguageEnum FR = new LanguageEnum("FR", "fr");
        public static readonly LanguageEnum EN = new LanguageEnum("EN", "en");
        



        public string Name { get; }
        public string Description { get; }
        private LanguageEnum(string name,string description)
        {
            Name = name ;
            Description = description;
        }

    }
}

using System;
using System.Collections.Generic;


namespace Assets._Project.Localization.Localization
{
    [Serializable]
    public class LocalizationData 
	{
		public List<LocalizationItem> items;
		public Dictionary<string, string> ToDictionary()
		{
			Dictionary<string, string> dict = new Dictionary<string, string>();

			if (items == null)
			{
				return dict;
			}

			foreach (var item in items)
			{
                try
                {
					dict[item.key] = item.value;
				}
				catch (ArgumentNullException)
                {
					 
				}
			}
			return dict;
        }

    }
}
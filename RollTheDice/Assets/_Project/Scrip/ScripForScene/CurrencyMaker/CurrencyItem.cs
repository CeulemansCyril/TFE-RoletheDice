using Assets._Project.API.Model.Object.Game.Money;
using Assets._Project.Scrip.ScripUI.RenameField;
using System;
using System.Collections;
using System.Linq;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.CurrencyMaker
{
	public class CurrencyItem: MonoBehaviour
	{
		[SerializeField] private RenameField label;
        [SerializeField] private RenameField code;
        [SerializeField] private RenameField symbol;
        [SerializeField] private RenameField baseValue;

		[SerializeField] private Button buttonDel;

		public Action<CurrencyItem> actionDel;


		public Currency currency;
		 
		public void Init(Currency currency)
		{
			label.SetText(currency.Name)  ;

            baseValue.Setup(EndRenameBasicValue);
            symbol.Setup(EndRenameSymbole);
            code.Setup(EndRenameCode);


            code.SetText(currency.Code);
			 
            symbol.SetText(currency.Symbol);
	
			baseValue.SetText(currency.BaseUnit+"");
			 

			this.currency = currency;

			buttonDel.onClick.AddListener(DelAction);
        }

		private void DelAction()
		{
			actionDel?.Invoke(this);
			Destroy(gameObject);
		}
 

		public Currency GetCurrency()
		{
			if(currency == null) return null;

			if(! string.IsNullOrEmpty(label.GetTitle())) currency.Name = label.GetTitle();
			if(!string.IsNullOrEmpty(code.GetTitle())) currency.Code = code.GetTitle();
			if(! string.IsNullOrEmpty(symbol.GetTitle()) ) currency.Symbol = symbol.GetTitle();
			if(! string .IsNullOrEmpty(baseValue.GetTitle()) ) currency.BaseUnit = int.Parse(baseValue.GetTitle());

			return currency;
		}

		private void EndRenameCode()
		{
          
            string txt = code.GetTitle();
			if (!string.IsNullOrEmpty(txt))
			{
				code.SetText(txt.Length > 3 ? txt[..3].ToUpper() : txt.ToUpper());
     
            }else code.SetText(currency.Code);
		}

		private void EndRenameSymbole()
		{
         
            string txt = symbol.GetTitle();
            if (!string.IsNullOrEmpty(txt))
			{
                symbol.SetText(txt.Length > 1 ? txt[..1].ToUpper() : txt.ToUpper());
            }
            else symbol.SetText(currency.Symbol);

        }

        private void EndRenameBasicValue()
        {
          
            string txt = baseValue.GetTitle();

            if (!string.IsNullOrEmpty(txt))
            {
                
                string digitsOnly = new string(txt.Where(char.IsDigit).ToArray());

                if (!string.IsNullOrEmpty(digitsOnly))
                {
                    baseValue.SetText(int.Parse(digitsOnly).ToString());
                }
                else
                {
                    baseValue.SetText(currency.BaseUnit+"");
                }
            }
            else
            {
                baseValue.SetText(currency.BaseUnit+"");
            }
        }


    }
}
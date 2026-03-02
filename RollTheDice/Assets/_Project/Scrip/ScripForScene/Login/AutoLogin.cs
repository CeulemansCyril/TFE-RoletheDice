using UnityEngine;
using System.Collections;

namespace Assets._Project.Scrip.ScripForScene.Login
{
	public class AutoLogin: MonoBehaviour
	{
 
		void Start()
		{
			if(PlayerPrefs.HasKey("JWT"))
			{
				UserSession.Intance.JWT = PlayerPrefs.GetString("JWT");
				UserSession.Intance.UserID = long.Parse( PlayerPrefs.GetString("UserID"));
				UserSession.Intance.Role = (API.Enums.RoleUser)PlayerPrefs.GetInt("Role");
                SceneLoader.Instance.LoadScene(Scene.Scene.MainMenu);
            }

		}

		 
	}
}
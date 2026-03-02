using UnityEngine;
using System.Collections;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.Login
{
	public class LogOut: MonoBehaviour
	{
		[SerializeField] private Button ButtonLogOut;

		private void Start()
		{
			ButtonLogOut.onClick.AddListener(OnLogOut);

        }

		private void OnLogOut()
		{
            PlayerPrefs.DeleteKey("JWT");
            PlayerPrefs.DeleteKey("UserID");
            PlayerPrefs.DeleteKey("Role");
            PlayerPrefs.Save();

			UserSession.Intance.JWT = null;
			UserSession.Intance.UserID = -1;
	 


            UnityEngine.SceneManagement.SceneManager.LoadScene(Scene.Scene.Login);

        }
    }
}
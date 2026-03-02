using UnityEngine;
using System.Collections;
using Assets._Project.API.Enums;

namespace Assets._Project.Scrip.ScripForScene.Login
{
	public class UserSession: MonoBehaviour
	{
		public static UserSession Intance { get; private set; }
	

        public string JWT { get; set; }
		public long UserID { get; set; }
		public RoleUser Role { get; set; }

        void Awake()
        {
            if (Intance == null)
            {
                Intance = this;
                DontDestroyOnLoad(gameObject);
            }
            else
            {
                Destroy(gameObject);
            }
        }
    }
}
using Assets._Project.API.Model.Object.Game;
using System;
using System.Collections.Generic;
using System.Text;
using UnityEngine;

namespace Assets._Project.Scrip.ScripForScene.Bundle
{
    public class BundleSession : MonoBehaviour
    
    {
        public static BundleSession Intance { get; private set; }

        public GameBundle Bundle { get; set; }

        private void Awake()
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

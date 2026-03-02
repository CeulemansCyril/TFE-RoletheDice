using Assets._Project.API.Model.Object.Game;
using System;
using System.Collections.Generic;
using System.Text;
using UnityEngine;

namespace Assets._Project.Scrip.ScripForScene.Bundle
{
    class BundleSession : MonoBehaviour
    {
        public GameBundle Bundle { get; private set; }
        public static BundleSession Instant { get; private set; }

        private void Awake()
        {
            if (Instant != null)
            {
                Destroy(gameObject);
                return;
            }
            Instant = this;
            DontDestroyOnLoad(gameObject);
        }
    }


}

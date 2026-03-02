using Assets._Project.API.Interface;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;

using Assets._Project.API.Model.DTO.Login;
using UnityEngine;

namespace Assets._Project.API.Service.Login
{
     public class LoginService : APILoginService
    {
        public LoginService() : base("auth/") { }

        public IEnumerator Login(
       LoginRequest dto,
       System.Action<JwtReponse> onSuccess,
       System.Action<string> onError)
        {
            return Post<LoginRequest, JwtReponse>(
                "login",
                dto,
                onSuccess,
                onError
            );
        }

        public IEnumerator Register(
            RegisterRequest dto,
            System.Action<string> onSuccess,
            System.Action<string> onError)
        {
        
            return Post<RegisterRequest,string>(
                "register",
                dto,
                onSuccess,
                onError
            );
        }
 
    }
}

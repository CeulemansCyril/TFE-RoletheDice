using Assets._Project.API.Model.DTO.Login;
using Assets._Project.API.Service.Login;
using Assets._Project.Localization;
using System.Collections;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.Login
{
	public class Register: MonoBehaviour
	{
        [SerializeField] private TMP_InputField username;
        [SerializeField] private TMP_InputField password;
		[SerializeField] private TMP_InputField confirmePassword;
        [SerializeField] private TMP_InputField email;

        [SerializeField] private Button register;

        [SerializeField] private TMP_Text errorLabel;

        private LoginService loginService;

        private void Start()
		{
            loginService = new LoginService();

            username.onValueChanged.AddListener(_ => errorLabel.text = "");
            password.onValueChanged.AddListener(_ => errorLabel.text = "");
            confirmePassword.onValueChanged.AddListener(_ => errorLabel.text = "");
            email.onValueChanged.AddListener(_ => errorLabel.text = "");

            register.onClick.AddListener(RegisterSetUp);
        }

        private void RegisterSetUp()
        {
            register.interactable = false;

            bool usernameEmpty = string.IsNullOrWhiteSpace(username.text);
            bool confirmePasswordEmpty = string.IsNullOrWhiteSpace(confirmePassword.text);
            bool passwordEmpty = string.IsNullOrWhiteSpace(password.text);
            bool emailEmpty = string.IsNullOrWhiteSpace(email.text);
            bool passwordIsDefferent = password.text.Equals(confirmePassword.text);

            if (usernameEmpty)
            {
                errorLabel.text = LocalizationControllers.Instance
                    .GetLocalizedValue("login.usernameEmpty");

                register.interactable = true;
                return;
            }

            if (passwordEmpty)
            {
                errorLabel.text = LocalizationControllers.Instance
                    .GetLocalizedValue("login.passwordEmpty");

                register.interactable = true;
                return;
            }

            if (confirmePasswordEmpty)
            {
                errorLabel.text = LocalizationControllers.Instance
                    .GetLocalizedValue("login.confirmePasswordEmpty");

                register.interactable = true;
                return;
            }

            if (emailEmpty)
            {
                errorLabel.text = LocalizationControllers.Instance
                    .GetLocalizedValue("login.emailEmpty");

                register.interactable = true;
                return;
            }

            if (!passwordIsDefferent)
            {
                errorLabel.text = LocalizationControllers.Instance
                    .GetLocalizedValue("login.passwordIsDefferent");

                register.interactable = true;
                return;
            }

            RegisterRequest registerRequest = new RegisterRequest()
            {
                Username = username.text,
                Password = password.text,
                Email = email.text,
            };

            StartCoroutine(
                 loginService.Register(registerRequest, Succes, Error)
            ) ;

        }


        private void Succes(string jwt)
        {
            register.interactable = true;
            errorLabel.text = "";
            Debug.Log("Login réussi !");
        }

        private void Error(string error)
        {
            register.interactable = true;
            errorLabel.text = error;
            Debug.LogError(error);
        }
    }
}
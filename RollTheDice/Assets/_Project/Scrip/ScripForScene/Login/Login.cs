using Assets._Project.API.Model.DTO.Login;
using Assets._Project.API.Service.Login;
using Assets._Project.Localization;
using Assets._Project.Scrip.Scene;
using TMPro;
 
using UnityEngine;
using UnityEngine.UI;

namespace Assets._Project.Scrip.ScripForScene.Login
{
    public class Login : MonoBehaviour
    {
       [SerializeField]  private TMP_InputField username;
       [SerializeField] private TMP_InputField password;
       [SerializeField] private Button login;

        [SerializeField] private TMP_Text errorLabel;

        private LoginService loginService;

        private void Start()
        {
            loginService = new LoginService();
            login.onClick.AddListener(LoginSetup);

            username.onValueChanged.AddListener(_ => errorLabel.text = "");
            password.onValueChanged.AddListener(_ => errorLabel.text = "");
        }


        private void LoginSetup()
        {
            login.interactable = false;
            bool usernameEmpty = string.IsNullOrWhiteSpace(username.text);
            bool passwordEmpty = string.IsNullOrWhiteSpace(password.text);

            if (usernameEmpty && passwordEmpty)
            {
                errorLabel.text = LocalizationControllers.Instance
                    .GetLocalizedValue("login.passwordAndLoginEmpty");
                login.interactable = true;
                return;
            }

            if (usernameEmpty)
            {
                errorLabel.text = LocalizationControllers.Instance
                    .GetLocalizedValue("login.usernameEmpty");
                login.interactable = true;
                return;
            }

            if (passwordEmpty)
            {
                errorLabel.text = LocalizationControllers.Instance
                    .GetLocalizedValue("login.passwordEmpty");
                login.interactable = true;
                return;
            }

          
            LoginRequest loginRequest = new LoginRequest
            {
                Username = username.text,
                Password = password.text
            };

            StartCoroutine(
                loginService.Login(loginRequest, Succes, Error)
            );


        }

        private void Succes(JwtReponse jwt)
        {
            login.interactable = true;
            errorLabel.text = "";
            UserSession.Intance.JWT = jwt.Token;
            UserSession.Intance.UserID = jwt.IdUser;
            UserSession.Intance.Role = jwt.Role;

            PlayerPrefs.SetString("JWT", jwt.Token);
            PlayerPrefs.SetString("UserID", jwt.IdUser.ToString());
            PlayerPrefs.SetString("Role", jwt.Role.ToString());
            PlayerPrefs.Save();

            SceneLoader.Instance.LoadScene(Scene.Scene.MainMenu);
            Debug.Log("Login réussi !");
        }

        private void Error(string error)
        {
            login.interactable = true;
            errorLabel.text = error;
            Debug.LogError(error);
        }

    }
}

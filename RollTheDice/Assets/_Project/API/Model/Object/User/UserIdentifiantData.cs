
namespace RollTheDice.API.Models.Users
{
    public class UserIdentifantData
    {
        public long IdUser { get; set; }
        public string UserName { get; set; }

        public UserIdentifantData() { }
        public UserIdentifantData(long id, string userName)
        {
            IdUser = id;
            UserName = userName;
        }

    }
}
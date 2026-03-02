using Assets._Project.API.Enums;
using System;
using System.Collections.Generic;
using System.Text;

namespace Assets._Project.API.Model.Object.User
{
    public class UserCreationContent
    {
        public long Id { get; set; }
        public Users Users { get; set; }
        public CreatedItemType CreatedItemType { get; set; }

        public UserCreationContent() { }

        public UserCreationContent(long id, Users users,CreatedItemType createdItemType) {
            Id = id;
            Users = users;
            CreatedItemType = createdItemType;
        }

    }
}

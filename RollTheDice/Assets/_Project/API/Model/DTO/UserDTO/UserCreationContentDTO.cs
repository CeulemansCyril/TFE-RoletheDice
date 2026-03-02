using Assets._Project.API.Enums;
using System;
using System.Collections.Generic;
using System.Text;

namespace Assets._Project.API.Model.DTO.UserDTO
{
    public class UserCreationContentDTO
    {
        private long Id { get; set; }
        private long UserId { get; set; }
        private long CreatedItemId { get; set; }
        private CreatedItemType CreatedItemType { get; set; }
        
        public UserCreationContentDTO() { }
        public UserCreationContentDTO(long id, long userId, long createdItemId, CreatedItemType createdItemType)
        {
            Id = id;
            UserId = userId;
            CreatedItemId = createdItemId;
            CreatedItemType = createdItemType;
        }
    }
}

using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.Object;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using UnityEngine;

namespace Assets._Project.API.Service
{
    public class MessagesService : ApiService
    {
        private CatchError onError;
        public MessagesService() : base("messages") { }
        
        public Awaitable<MessageDTO> CreateMessages<MessageDTO>(MessageDTO message)
        {
            return CreateAsync("/create", message);
        }

        public Awaitable<MessageDTO> UpdateMessage(MessageDTO message)
        {
            return UpdateAsync("/update ", message);
        }

        public Awaitable<string> DeleteMessage(long id)
        {
            return DeleteAsync("/delete/" + id);
        }

        public Awaitable<MessageDTO> GetMessageById<MessageDTO>(long id)
        {
            return GetAsync<MessageDTO>("/getById/" + id);
        }

        public Awaitable<MessageDTO[]> GetAllByConversationId<MessageDTO>(long id)
        {
            return GetAllAsync<MessageDTO>("/getAllByConversationId/" + id);
        }
 


        public Message MessageDTOToMessage(MessageDTO dto)
        {
            Message message = new Message();
            message.Id = dto.Id;
            message.Content = dto.Content;
            message.Sender = dto.Sender;
            message.SentAt = dto.SentAt;
            message.IsRead = dto.IsRead;
            message.IsModified = dto.IsModified;
            return message;
        }

        public MessageDTO MessageToMessageDTO(Message message, long idConversation)
        {
            MessageDTO dto = new MessageDTO();
            dto.Id = message.Id;
            dto.Content = message.Content;
            dto.Sender = message.Sender;
            dto.SentAt = message.SentAt;
            dto.IsRead = message.IsRead;
            dto.IsModified = message.IsModified;
            dto.IdConversation = idConversation;
            return dto;
        }

    }
}

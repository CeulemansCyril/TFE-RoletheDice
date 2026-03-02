
using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.DTO.ChatDTO;
using Assets._Project.API.Model.Object.Chat;
using Assets._Project.API.Model.Object.User;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace Assets._Project.API.Service.Chat
{
    public class ChatService : ApiService 
    {
         private CatchError onError;
        public ChatService(string endpoint) : base("chat")
        {
        }


         // ------------------- ChatChanel -------------------------

        
        public Awaitable<ChatChanelDTO> CreateChatChanel<ChatChanelDTO>(ChatChanelDTO chat)
        {
            return CreateAsync("/CreateChat", chat);
        }

        public Awaitable<ChatChanelDTO> UpdateChatChanel <ChatChanelDTO>(ChatChanelDTO chat)
        {
            return UpdateAsync("/UpdateChat", chat);
        }

        public Awaitable<ChatChanelDTO[]> GetAllChatByGameId <ChatChanelDTO>(long id)
        {
            return GetAllAsync<ChatChanelDTO>("/GetALlChatByGameId/"+id);
        }

        public Awaitable<string> DeleteChatChanel (long id)
        {
            return DeleteAsync("/DeleteChat/"+id);
        }
 

        public ChatChanel ChatChanelDTOToChatChanel(ChatChanelDTO chatChanelDTO)
        {
            ChatChanel chatChanel = new ChatChanel();
            chatChanel.Id = chatChanelDTO.Id;
            chatChanel.Name = chatChanelDTO.Name;
            chatChanel.IdGame = chatChanelDTO.IdGame;
            chatChanel.ChatMessages = new List<ChatMessage>();
            return chatChanel;
        }

        public ChatChanelDTO ChatChanelToChatChanelDTO(ChatChanel chatChanel)
        {
            ChatChanelDTO chatChanelDTO = new ChatChanelDTO();
            chatChanelDTO.Id = chatChanel.Id;
            chatChanelDTO.Name = chatChanel.Name;
            chatChanelDTO.IdGame = chatChanel.IdGame;
            chatChanelDTO.IdMessages = new List<long>();

            foreach (var message in chatChanel.ChatMessages)
            {
                chatChanelDTO.IdMessages.Add(message.Id);
            }

            return chatChanelDTO;
        }


         // ------------------- ChatMessage -------------------------

      

        public Awaitable<ChatMessageDTO> CreateChatMessage<ChatMessageDTO>(ChatMessageDTO chat)
        {
            return CreateAsync("/CreateMessage", chat);
        }

        public Awaitable<ChatMessageDTO[]> CreateManyChatMessage<ChatMessageDTO>(ChatMessageDTO[] chat)
        {
            return CreateAsync("/CreateManyMessage", chat);
        }

        public Awaitable<ChatMessageDTO> UpdateChatMessage <ChatMessageDTO>(ChatMessageDTO chat)
        {
            return UpdateAsync("/UpdateMessage", chat);
        }

        public Awaitable<ChatMessageDTO[]> GetAllMessageByGameId <ChatMessageDTO>(long id)
        {
            return GetAllAsync<ChatMessageDTO>("/GetALlMessageByGameId/"+id);
        }

       public Awaitable<string> DeleteChatMessageAsync (long id)
        {
            return DeleteAsync("/DeleteMessage/"+id);
        }
 

        public ChatMessage ChatMessageDTOToChatMessage(ChatMessageDTO chatMessageDTO)
        {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.Id = chatMessageDTO.Id;
            chatMessage.Message = chatMessageDTO.MessageContent;
            chatMessage.SentAt = chatMessageDTO.SentAt;
            chatMessage.Sender = chatMessageDTO.IdSender;
            chatMessage.IsModified = chatMessageDTO.IsModified;
            chatMessage.idChatChanel = chatMessageDTO.IdChatChanel;
            return chatMessage;
        }

        public ChatMessageDTO ChatMessageToChatMessageDTO(ChatMessage chatMessage)
        {
            ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
            chatMessageDTO.Id = chatMessage.Id;
            chatMessageDTO.MessageContent = chatMessage.Message;
            chatMessageDTO.SentAt = chatMessage.SentAt;
            chatMessageDTO.IdSender = chatMessage.Sender;
            chatMessageDTO.IsModified = chatMessage.IsModified;
            chatMessageDTO.IdChatChanel = chatMessage.idChatChanel;
            return chatMessageDTO;
        }

    }
}
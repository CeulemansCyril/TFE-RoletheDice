using Assets._Project.API.Interface;
using Assets._Project.API.Model.DTO;
using Assets._Project.API.Model.Object;
using System.Collections;
using UnityEngine;

namespace Assets._Project.API.Service.Notification
{
    public class NotificationService : ApiService
    {
        private CatchError onError;

        public NotificationService() : base("Notifications") { }

        public Awaitable<NotificationDTO> CreateNotification<NotificationDTO>(NotificationDTO notification)
        {
            return CreateAsync("/createNotification", notification);
        }

        public Awaitable<NotificationDTO> GetNotificationById<NotificationDTO>(long id)
        {
            return GetAsync<NotificationDTO>("/getNotificationById/" + id);
        }
        
        public Awaitable<NotificationDTO[]> GetAllNotificationsByReceiverId<NotificationDTO>(long receiverId)
        {
            return GetAllAsync<NotificationDTO>("/getAllNotificationsByReceiverId/" + receiverId);
        }


        public Awaitable<NotificationDTO[]> GetUnreadNotificationsByReceiverId<NotificationDTO>(long receiverId)
        {
            return GetAllAsync<NotificationDTO>("/getAllNotificationsByReceiverIdNotRead/" + receiverId);
        }

        public Awaitable<NotificationDTO> MarkAsRead<NotificationDTO>(long receiverId)
        {
            return GetAsync<NotificationDTO>("/read/" + receiverId);
        }

        public Awaitable<NotificationDTO[]> MarkAllNotificationsAsRead<NotificationDTO>(long receiverId)
        {
            return GetAllAsync<NotificationDTO>("/setAllNotificationReadForOneUser/" + receiverId);
        }

        public Awaitable<NotificationDTO> CountUnreadNotifications<NotificationDTO>(long receiverId)
        {
            return GetAsync<NotificationDTO>("/count/unread/" + receiverId);
        }

        public Awaitable<string> deleteNotificationById(long id)
        {
            return GetAsync<string>("/deleteNotificationById/"+id);
        }

        public Awaitable<string> deleteAllNotificationsByReceiverId(long receiverId)
        {
            return GetAsync<string>("/deleteAllNotificationsByReceiverId/" + receiverId);
        }


     

        //TODO: Add more methods as needed, such as updating notification content, etc.
    }
}

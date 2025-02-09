package com.elibrary.LibraLink.services;


import com.elibrary.LibraLink.entities.Notification;
import com.elibrary.LibraLink.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    // CONSTANT VALUES
    private final NotificationRepository notificationRepository;

    // CONSTRUCTOR
    public NotificationService(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    //Create Notification
    public Notification addNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    //Get Notification By ID
    public Optional<Notification> findNotificationById(Integer id){
        return notificationRepository.findById(id);
    }

    //Get All Notifications
    public List<Notification> findAllNotifications(){
        return notificationRepository.findAll();
    }

    //Update Notification By id
    public Notification updateNotification(Notification notification){
        Optional<Notification> originalNotification = notificationRepository.findById(notification.getId());

        if(originalNotification.isPresent()){
            Notification notificationForUpdate = originalNotification.get();
            notificationForUpdate.setContent(notification.getContent());
            notificationForUpdate.setRead_status(false);
            notificationForUpdate.setSeen_status(false);

            return notificationRepository.save(notificationForUpdate);
        }else {
            throw new Error("Notification Not Found With Id "+notification.getId());
        }
    }

    //Delete Notification (soft)
    public void softDeleteNotification(Integer id){
        Optional<Notification> notification = notificationRepository.findById(id);
        if(notification.isPresent()){
            Notification notificationForDle = notification.get();
            notificationForDle.setStatus(false);

            notificationRepository.save(notificationForDle);
        }else{
            throw new Error("Notification Not Found With Id "+id);
        }
    }

    //Delete Notification (hard)
    public void permanentDeleteNotification(Integer id){
        Optional<Notification> notification = notificationRepository.findById(id);
        if(notification.isPresent()){
            notificationRepository.deleteById(id);
        }else{
            throw new Error("Notification Not Found With Id "+id);
        }
    }
}

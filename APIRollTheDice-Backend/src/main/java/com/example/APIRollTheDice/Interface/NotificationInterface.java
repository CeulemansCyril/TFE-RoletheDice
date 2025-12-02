package com.example.APIRollTheDice.Interface;

import com.example.APIRollTheDice.Model.Obj.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationInterface  extends JpaRepository<Notification, Long> {


    Optional<Notification> findById(Long id);

     List<Notification> findAllByReceiverId(Long receiverId);
     List<Notification> findAllByReceiverIdAndReadFalse(Long receiverId);

     boolean existsByReceiverId(Long receiverId);

    void deleteById(Long id);
    void deleteAllByReceiverId(Long receiverId);

    int countByReceiverId(Long receiverId);
}

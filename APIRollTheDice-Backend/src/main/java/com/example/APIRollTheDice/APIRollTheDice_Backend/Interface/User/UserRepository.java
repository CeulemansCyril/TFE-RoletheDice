package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.User;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

     User findByusername(String username);
     User findByEmail(String email);

     Optional<User> findById(Long id);
     List<User> findAllById(List<Long> id);

    boolean existsByusername(String username);
    boolean existsByEmail(String email);
    boolean existsByIdAndIsOnlineTrue(Long id);


    List<User> findAllByisDeletedFalse();


    @Query("""
    SELECT u FROM User u
    WHERE u.profilePublic = true
    AND LOWER(u.username) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
    AND u.id <> :currentUserId
    AND  u.roleUser = USER
    AND u.id NOT IN (
        SELECT b.id FROM User cu JOIN cu.blockedUsers b WHERE cu.id = :currentUserId
    )
    AND u.id NOT IN (
        SELECT bu.id FROM User bu JOIN bu.blockedUsers cbu WHERE cbu.id = :currentUserId
    )
    AND u.id NOT IN (
        SELECT f.id FROM User cu JOIN cu.friends f WHERE cu.id = :currentUserId
    )
""")
    List<User> searchAvailableUsers(@Param("currentUserId") Long currentUserId, @Param("searchTerm") String searchTerm);

}

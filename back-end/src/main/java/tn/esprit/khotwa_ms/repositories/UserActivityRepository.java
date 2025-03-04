package tn.esprit.khotwa_ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.khotwa_ms.entity.UserAction;
import tn.esprit.khotwa_ms.entity.UserActivity;

import java.util.List;

public interface UserActivityRepository extends JpaRepository<UserActivity,Long> {
    @Query("SELECT ua FROM UserActivity ua WHERE ua.user.id_user = :id_user")
    List<UserActivity> findActivitiesByUserId(@Param("id_user") Integer id_user);

    // Count occurrences of a specific action by user

    @Query("SELECT COUNT(ua) FROM UserActivity ua WHERE ua.user.id_user = :id_user AND ua.action = :action")
    Integer countByUserAndAction(@Param("id_user") Integer id_user, @Param("action") UserAction action);

    // Find the most recent activity of a specific user
    @Query("SELECT ua FROM UserActivity ua WHERE ua.user.id_user = :id_user AND ua.action = :action ORDER BY ua.action_date DESC")
    UserActivity findTopByUserAndActionOrderByActionDateDesc(@Param("id_user") Integer id_user, @Param("action") UserAction action);
    @Query("SELECT ua FROM UserActivity ua WHERE ua.user.id_user = :id_user")
    List<UserActivity> findByUser(@Param("id_user") Integer id_user);

    @Query("SELECT COUNT(ua) FROM UserActivity ua WHERE ua.user.id_user = :id_user")
    Integer countByUser(@Param("id_user") Integer id_user);
}
package tn.esprit.khotwa_ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.khotwa_ms.entity.UserAction;
import tn.esprit.khotwa_ms.entity.UserActivity;

import java.time.LocalDateTime;
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

    @Query("SELECT COUNT(a) FROM UserActivity a")
    long countTotalActions();

    @Query("SELECT COUNT(a) FROM UserActivity a WHERE a.action_date BETWEEN ?1 AND ?2")
    long countActionsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);


    //for analytics

    @Query("SELECT ua.user, COUNT(ua) as actions FROM UserActivity ua GROUP BY ua.user ORDER BY actions DESC")
    List<Object[]> findMostActiveUsers();
    @Query("SELECT FUNCTION('DATE', ua.action_date) as date, COUNT(ua) FROM UserActivity ua WHERE ua.action = :action GROUP BY FUNCTION('DATE', ua.action_date) ORDER BY date")
    List<Object[]> countLoginsPerDay(@Param("action") UserAction action);
    @Query("SELECT ua.action, COUNT(ua) FROM UserActivity ua GROUP BY ua.action")
    List<Object[]> countActionsByType();

    @Query("SELECT FUNCTION('HOUR', ua.action_date), COUNT(ua) FROM UserActivity ua GROUP BY FUNCTION('HOUR', ua.action_date) ORDER BY FUNCTION('HOUR', ua.action_date)")
    List<Object[]> countActionsByHour();
    @Query("SELECT FUNCTION('MONTH', ua.action_date), COUNT(ua) FROM UserActivity ua GROUP BY FUNCTION('MONTH', ua.action_date) ORDER BY FUNCTION('MONTH', ua.action_date)")
    List<Object[]> countMonthlyActivity();

}


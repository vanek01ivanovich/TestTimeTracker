package com.example.demo.persistence.repository;

import com.example.demo.data.entity.Project;
import com.example.demo.data.entity.Task;
import com.example.demo.data.entity.User;
import com.example.demo.data.enums.Status;
import com.example.demo.web.dto.TaskActivityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAllByUser(User user);

    @Query(value = "select t from Task t join fetch t.user where t.id = :taskId")
    Optional<Task> findByTaskId(@Param("taskId") UUID taskId);

    @Modifying
    @Query(value = "update Task t set t.user.id = :userId where t.id = :taskId")
    void assignTaskForUser(@Param("userId") UUID userId, @Param("taskId") UUID taskId);

    @Modifying
    @Query(value = "delete from Task t where t.id in " +
            "(select t.id from Task t inner join TaskTracker tt on tt.id = t.id where tt.status =:status) ")
    void deleteAllByStatus(@Param("status") Status status);

    void deleteAllByProject(Project project);

    @Query(value = "select t, tt from Task t inner join TaskTracker tt on tt.id = t.id " +
            "where tt.status = :status")
    List<TaskActivityDto> findAllByStatus(@Param("status") Status status);

    @Query(value = "select new com.example.demo.web.dto.TaskActivityDto(t.id,t.name," +
            "t.user ,tt.startedWhen, tt.finishedWhen, tt.duration,tt.status) " +
            "from Task t " +
            "inner join TaskTracker tt on tt.id = t.id " +
            "where tt.status = :status and t.user = :user")
    List<TaskActivityDto> findAllByUserAndStatus(@Param("user") User user, @Param("status") Status status);

    @Query(value = "select t, tt from Task t inner join TaskTracker tt on tt.id = t.id " +
            "where t.user = :user ")
    List<TaskActivityDto> findTaskAndTrackerByUser(@Param("user") User user);

    @Modifying
    @Query("update Task set user = null where user =:user")
    void deleteUserFromTask(@Param("user") User user);


}

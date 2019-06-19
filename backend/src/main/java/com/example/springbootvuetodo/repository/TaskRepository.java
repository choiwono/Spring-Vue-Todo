package com.example.springbootvuetodo.repository;

import com.example.springbootvuetodo.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query("SELECT t FROM Task t WHERE t.id=:id")
    Task getTaskById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Task t WHERE t.id=:id")
    void deleteTaskById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task as t SET t.complete='Y' WHERE t.id=:id",nativeQuery = true)
    void updateTaskComplete(@Param("id") Long id);

    @Query(value = "SELECT t FROM Task t WHERE t.timeOut=true")
    List<Task> findAllByTimeOut();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Task t SET t.timeOut=true WHERE t.endDate < CURRENT_TIMESTAMP AND t.complete='N'")
    void updateAllEndTask();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Task t SET t.title=:title, t.content=:content WHERE t.id=:id")
    void updateTask(@Param("id") Long id,@Param("title") String title,@Param("content") String content);
}

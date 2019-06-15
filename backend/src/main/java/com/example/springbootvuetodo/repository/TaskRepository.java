package com.example.springbootvuetodo.repository;

import com.example.springbootvuetodo.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query("SELECT t FROM Task t WHERE t.id=:id")
    public Task getTaskById(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Task t WHERE t.id=:id")
    public void deleteTaskById(@Param("id") Long id);
}

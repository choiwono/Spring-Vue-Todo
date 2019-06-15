package com.example.springbootvuetodo.service;

import com.example.springbootvuetodo.domain.Task;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {
    // 일추가
    Task addTask(Task task);
    Task getTask(Long id);
    void updateTask(Task task);
    void deleteTaskById(Long id);
    // 하나 완료처리
    void updateTaskComplete(Long id);
    // 한꺼번에 처리 및 task 변경
    void updateAllTaskComplete(List<Task> tasks);
    // 모든 todo 가져오기
    List<Task> getAllTasks();
    // 마감날짜가 지나지 않은 todo 가져오기
    List<Task> getTasksByTimeOut();
    // 마감날짜가 지나지 경우 완료처리
    void updateAllEndTask();
}

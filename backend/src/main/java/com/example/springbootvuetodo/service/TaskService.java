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
    // 한꺼번에 완료처리
    void deleteAllByList(List<Long> list);
    void updateAllTaskComplete(List<Task> tasks);
    // 모든 todo 가져오기
    List<Task> getAllTasks();
    // 마감날짜가 지나지 않은 todo 가져오기
    List<Task> getTasksByTimeOut();
    // 마감날짜가 지난 경우 타임아웃처리
    void updateAllEndTask();
    // 알람은 하루에 몇번 설정했는지 체크, 하루전일 경우 알람을 주자, 횟수는 설정에 따라서
    // 일단 하루전일 경우에 알람을 줄것!
    void updateAlarmTask(List<Long> list);
}

package com.example.springbootvuetodo.service;

import com.example.springbootvuetodo.domain.Task;
import com.example.springbootvuetodo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    // 추가
    @Override
    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.updateTask(task.getId(),task.getTitle(),task.getContent());
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteTaskById(id);
    }

    @Override
    public void updateAllTaskComplete(List<Task> tasks) {
        tasks.stream().forEach(x -> taskRepository.updateTaskComplete(x.getId()));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByTimeOut() {
        return taskRepository.findAllByTimeOut();
    }

    @Override
    public void updateAllEndTask() {
        taskRepository.updateAllEndTask();
    }

    @Override
    public void deleteAllByList(List<Long> list) {
        list.stream().forEach(t -> taskRepository.deleteTaskById(t.longValue()));
    }

    // 알람처리
    /*@Override
    public void updateAlarmTask(List<Long> list) {
        //list.stream().forEach(t -> taskRepository.updateAlaramById(t));
    }*/
}

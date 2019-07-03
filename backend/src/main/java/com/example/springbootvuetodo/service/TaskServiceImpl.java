package com.example.springbootvuetodo.service;

import com.example.springbootvuetodo.domain.Task;
import com.example.springbootvuetodo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
    public void updateTask(Task task){
        taskRepository.save(task);
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
        Sort sort = new Sort(Sort.Direction.DESC, "endDate","priorityOrderType");
        return taskRepository.findAll(sort);
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

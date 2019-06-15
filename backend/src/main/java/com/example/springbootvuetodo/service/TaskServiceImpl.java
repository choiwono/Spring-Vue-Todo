package com.example.springbootvuetodo.service;

import com.example.springbootvuetodo.domain.Task;

import java.util.List;

public class TaskServiceImpl implements TaskService{
    @Override
    public Task addTask(Task task) {
        return null;
    }

    @Override
    public Task getTask(Long id) {
        return null;
    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void deleteTaskById(Long id) {

    }

    @Override
    public void updateTaskComplete(Long id) {

    }

    @Override
    public void updateAllTaskComplete(List<Task> tasks) {

    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public List<Task> getTasksByTimeOut() {
        return null;
    }

    @Override
    public void updateAllEndTask() {

    }
}

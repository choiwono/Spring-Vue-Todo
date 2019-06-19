package com.example.springbootvuetodo.util;

import com.example.springbootvuetodo.domain.Task;
import com.example.springbootvuetodo.dto.TaskDto;
import org.modelmapper.ModelMapper;

public class ConverToEntity {
    public Task converToEntity(TaskDto taskDto){
        ModelMapper modelMapper = new ModelMapper();
        Task task = modelMapper.map(taskDto, Task.class);
        return task;
    }
}

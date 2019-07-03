package com.example.springbootvuetodo.util;

import com.example.springbootvuetodo.domain.Task;
import com.example.springbootvuetodo.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

public class ConverToEntity {
    public Task converToEntity(TaskDto taskDto){
        ParseStringToDate parseStringToDate = new ParseStringToDate();
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        task.setPriorityOrderType(taskDto.getPriorityOrderType());
        task.setContent(taskDto.getContent());
        task.setEndDate(parseStringToDate.stringToParse(taskDto.getEndDate()));
        return task;
    }
}

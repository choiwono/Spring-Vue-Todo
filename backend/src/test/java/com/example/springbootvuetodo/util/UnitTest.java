package com.example.springbootvuetodo.util;

import com.example.springbootvuetodo.domain.Task;
import com.example.springbootvuetodo.dto.TaskDto;
import com.example.springbootvuetodo.service.TaskService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTest {
    @Autowired
    TaskService taskService;

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void DTO_TO_ENTITY_테스트(){
        Task task = taskService.getTask(1L);
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("제목3333");
        taskDto.setContent("내용3333");

        Task task1 = modelMapper.map(taskDto, Task.class);
        Assert.assertEquals(task1.getTitle(),task.getTitle());
        Assert.assertEquals(task1.getContent(),task.getContent());
    }
}

package com.example.springbootvuetodo.repository;

import com.example.springbootvuetodo.domain.Task;
import com.example.springbootvuetodo.util.PriorityOrderType;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {
    @Autowired
    TaskRepository taskRepository;

    @Test
    public void 모두_완료상태값으로_변경(){
        List<Task> tasks =
                Arrays.asList(new Task(1L,"제목1","내용1", PriorityOrderType.Low),
                              new Task(2L,"제목2","내용2", PriorityOrderType.Low),
                              new Task(3L,"제목2","내용2", PriorityOrderType.Low));

        for(int i=0; i<tasks.size(); i++)
            taskRepository.updateTaskComplete(tasks.get(i).getId());
    }

    @Test
    public void TODO_1개_가져오기(){
        Task task = taskRepository.getTaskById(1L);
        Assert.assertEquals(1L,(long)task.getId());
    }

    @Test
    @Transactional
    public void TODO_1개_삭제(){
        taskRepository.deleteTaskById(1L);
        Task task = taskRepository.getTaskById(1L);
        Assert.assertEquals(task,null);
    }

    @Test
    @Transactional
    public void TODO_제목_내용(){
        Task task1 = taskRepository.getTaskById(1L);
        //taskRepository.updateTask(1L,"제목333","내용5555");
        Task task2 = taskRepository.getTaskById(1L);
        Assert.assertEquals(task1.getTitle(),task2.getTitle());
    }

    @Test
    public void TODO_LIST_모두_가져오기(){
        List<Task> tasks = taskRepository.findAll();
        tasks.stream().forEach(t -> System.out.println(t.getId()));
    }

    @Test
    public void TIME_OUT_된_TODO_LIST_모두가져오기(){
        List<Task> tasks = taskRepository.findAllByTimeOut();
        tasks.stream().forEach(t -> System.out.println(t.isTimeOut()));
    }

    @Test
    @Transactional
    public void 마감기한지난_TODO_LIST_모두_TIME_OUT_처리(){
        taskRepository.updateAllEndTask();
        List<Task> tasks = taskRepository.findAll();
        tasks.stream().forEach(t -> System.out.println(t.isTimeOut()));
    }
}

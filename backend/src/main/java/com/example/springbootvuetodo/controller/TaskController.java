package com.example.springbootvuetodo.controller;

import com.example.springbootvuetodo.domain.Task;
import com.example.springbootvuetodo.dto.ResponseDto;
import com.example.springbootvuetodo.dto.TaskDto;
import com.example.springbootvuetodo.service.TaskService;
import com.example.springbootvuetodo.util.ConverToEntity;
import com.example.springbootvuetodo.util.ParseStringToDate;
import com.example.springbootvuetodo.util.PriorityOrderType;
import com.sun.corba.se.spi.orb.ParserData;
import jdk.nashorn.internal.parser.DateParser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ResponseDto> addTask(@Valid TaskDto taskDto, BindingResult bindingResult){
        ResponseDto responseDto = new ResponseDto();
        ParseStringToDate parseStringToDate = new ParseStringToDate();
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setContent(taskDto.getContent());
        String endDate = taskDto.getEndDate();
        // null 체크 후 마감기한 넣기
        if(Optional.ofNullable(endDate).isPresent())
            task.setEndDate(parseStringToDate.stringToParse(endDate));

        PriorityOrderType priorityType = taskDto.getPriorityOrderType();
        if(Optional.ofNullable(priorityType).isPresent())
            task.setPriorityOrderType(priorityType);

        if(Optional.ofNullable(taskService.addTask(task)).isPresent())
            responseDto.setMessage("OK! created");

        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Task> getTask(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(taskService.getTask(id),HttpStatus.OK);
    }

    @GetMapping(value="/all")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> list = taskService.getAllTasks();
        return new ResponseEntity<List<Task>>(list,HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseDto> updateTask(TaskDto taskDto){
        ResponseDto responseDto = new ResponseDto();
        ConverToEntity converToEntity = new ConverToEntity();
        taskService.updateTask(converToEntity.converToEntity(taskDto));
        responseDto.setMessage("OK, updated");
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseDto> deleteTask(@PathVariable(value = "id") Long id){
        ResponseDto responseDto = new ResponseDto();
        taskService.deleteTaskById(id);
        responseDto.setMessage("OK, deleted");
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }

    @PutMapping(value="/completes")
    public ResponseEntity<ResponseDto> updateComplete(List<TaskDto> taskDtos){
        ResponseDto responseDto = new ResponseDto();
        ConverToEntity converToEntity = new ConverToEntity();
        List<Task> list = taskDtos.stream().map(taskDto -> converToEntity.converToEntity(taskDto))
                .collect(Collectors.toList());
        taskService.updateAllTaskComplete(list);
        responseDto.setMessage("OK, updated");
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }

    @GetMapping(value="/timeout")
    public ResponseEntity<List<Task>> getListTimeOut(){
        return new ResponseEntity<>(taskService.getTasksByTimeOut(),HttpStatus.OK);
    }

    @PutMapping(value="/timeout")
    public ResponseEntity<ResponseDto> updateAllEndTask(){
        ResponseDto responseDto = new ResponseDto();
        taskService.updateAllEndTask();
        responseDto.setMessage("OK, updated All Tasks");
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }
}

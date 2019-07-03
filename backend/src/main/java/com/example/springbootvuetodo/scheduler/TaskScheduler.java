package com.example.springbootvuetodo.scheduler;

import com.example.springbootvuetodo.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TaskScheduler {
    private final TaskService taskService;
}

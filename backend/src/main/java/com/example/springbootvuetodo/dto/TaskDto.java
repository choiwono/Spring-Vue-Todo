package com.example.springbootvuetodo.dto;

import com.example.springbootvuetodo.util.PriorityOrderType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaskDto {
    private Long id;
    @NotNull
    private String title;
    private String content;
    private PriorityOrderType priorityOrderType;
    private String endDate;
}

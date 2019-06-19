package com.example.springbootvuetodo.dto;

import com.example.springbootvuetodo.util.PriorityOrderType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TaskDto {
    @NotNull(message = "제목을 입력해주세요")
    @Size(min=2)
    private String title;
    private String content;
    private PriorityOrderType priorityOrderType;
    private String endDate;
}

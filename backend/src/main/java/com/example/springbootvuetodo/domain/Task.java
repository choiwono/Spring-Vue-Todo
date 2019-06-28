package com.example.springbootvuetodo.domain;

import com.example.springbootvuetodo.util.PriorityOrderType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="task")
@Builder
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String content;

    @Column(name="priority_order_type")
    @Enumerated(EnumType.STRING)
    private PriorityOrderType priorityOrderType;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Seoul")
    @Column(name="reg_date")
    private Date regDate;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Seoul")
    @Column(name="end_date")
    private Date endDate;

    @Column(name="alarm_count")
    private int alarmCount;

    @Column(length = 1)
    private Character complete;
    @Column(name="time_out")
    private boolean timeOut;

    public Task(){
        regDate = new Date();
        endDate = new Date();
        complete = 'N';
        timeOut = false;
        alarmCount = 0;
        priorityOrderType = PriorityOrderType.Low;
    }

    public Task(Long id, String title, String content,PriorityOrderType priorityOrderType){
        this.id = id;
        this.title = title;
        this.content = content;
        this.priorityOrderType = priorityOrderType;
        regDate = new Date();
        endDate = new Date();
        complete = 'N';
        timeOut = false;
    }
}

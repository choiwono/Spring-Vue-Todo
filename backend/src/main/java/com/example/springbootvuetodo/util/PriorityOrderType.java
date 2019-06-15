package com.example.springbootvuetodo.util;

import java.util.stream.Stream;

public enum PriorityOrderType {
    High("H"), Medium("M"), Low("L");
    private String priority;

    private PriorityOrderType(String priority){
        this.priority = priority;
    }

    public String getPriority(){
        return priority;
    }

    public static PriorityOrderType of(String priority){
        return Stream.of(PriorityOrderType.values())
                .filter(p -> p.getPriority() == priority)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

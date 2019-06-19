package com.example.springbootvuetodo.util;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
public class ParseStringToDate {
    public Date stringToParse(String dateStr){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = dateFormat.parse(dateStr);
            return date;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return date;
    }
}

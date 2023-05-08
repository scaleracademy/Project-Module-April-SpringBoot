package com.scaler.springboot1.task.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseBodyDTO {
    private String name;
    private Date dueDate;
    private Boolean completed;
}

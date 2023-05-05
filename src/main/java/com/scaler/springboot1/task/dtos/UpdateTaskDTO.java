package com.scaler.springboot1.task.dtos;

import lombok.*;

@Data
public class UpdateTaskDTO{
    String dueDate;
    Boolean completed;
}

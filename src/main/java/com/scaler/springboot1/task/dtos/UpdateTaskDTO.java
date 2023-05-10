package com.scaler.springboot1.task.dtos;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
public class UpdateTaskDTO{
    Date dueDate;
    Boolean completed;
}

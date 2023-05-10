package com.scaler.springboot1.task.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TaskDTO {
    String name;
    Date dueDate;
}
